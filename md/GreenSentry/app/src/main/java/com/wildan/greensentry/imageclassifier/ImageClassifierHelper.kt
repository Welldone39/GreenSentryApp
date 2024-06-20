package com.wildan.greensentry.imageclassifier

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.provider.MediaStore
import android.util.Log
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException

class ImageClassifierHelper(
    val context: Context,
    val classifierListener: ClassifierListener?
) {
    private var interpreter: Interpreter? = null
    private var inputImageBuffer: TensorImage? = null
    private var outputBuffer: TensorBuffer? = null

    // Definisikan label untuk dua kategori: Organik dan Non-Organik
    private val labels = listOf("Organik", "Non-Organik")

    init {
        setupInterpreter()
    }

    private fun setupInterpreter() {
        try {
            val model = FileUtil.loadMappedFile(context, "model_waste.tflite")
            val options = Interpreter.Options()
            interpreter = Interpreter(model, options)

            // Inisialisasi buffer input dan output
            inputImageBuffer = TensorImage(DataType.FLOAT32)
            outputBuffer = TensorBuffer.createFixedSize(intArrayOf(1, labels.size), DataType.FLOAT32)
        } catch (e: IOException) {
            Log.e(TAG, "Kesalahan saat menginisialisasi interpreter TensorFlow Lite: ${e.message}")
            classifierListener?.onError("Kesalahan saat menginisialisasi interpreter TensorFlow Lite: ${e.message}")
        } catch (e: Exception) {
            Log.e(TAG, "Kesalahan tak terduga saat menginisialisasi interpreter TensorFlow Lite: ${e.message}")
            classifierListener?.onError("Kesalahan tak terduga saat menginisialisasi interpreter TensorFlow Lite: ${e.message}")
        }
    }

    fun classifyStaticImage(imageUri: Uri) {
        if (interpreter == null) {
            classifierListener?.onError("Interpreter TensorFlow Lite belum diinisialisasi.")
            return
        }

        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(context.contentResolver, imageUri)
            ImageDecoder.decodeBitmap(source)
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
        }.copy(Bitmap.Config.ARGB_8888, true)

        Log.d(TAG, "Lebar bitmap: ${bitmap.width}, tinggi: ${bitmap.height}")
        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(64, 64, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR)) // Sesuaikan ukuran dengan input model
            .add(NormalizeOp(127.5f, 127.5f)) // Normalisasi berdasarkan mean dan stddev model
            .build()

        inputImageBuffer?.load(bitmap)
        inputImageBuffer = imageProcessor.process(inputImageBuffer)
        Log.d(TAG, "Dimensi buffer gambar input: ${inputImageBuffer?.width}, ${inputImageBuffer?.height}")

        val inferenceStartTime = SystemClock.uptimeMillis()
        interpreter?.run(inputImageBuffer?.buffer, outputBuffer?.buffer?.rewind())
        val inferenceTime = SystemClock.uptimeMillis() - inferenceStartTime

        val probabilities = outputBuffer?.floatArray
        Log.d(TAG, "Probabilitas: ${probabilities?.contentToString()}")

        val resultLabel = if (probabilities != null && probabilities.isNotEmpty()) {
            if (probabilities[0] > 0.5) "Non-Organik" else "Organik"
        } else {
            "Tidak Diketahui"
        }

        Log.d(TAG, "Label hasil: $resultLabel")
        classifierListener?.onResults(resultLabel, inferenceTime)
    }

    interface ClassifierListener {
        fun onError(pesan: String)
        fun onResults(resultLabel: String?, waktuInferensi: Long)
    }

    companion object {
        private const val TAG = "ImageClassifierHelper"
    }
}
