# Green Sentry ML

## Waste Classification with Data Augmentation
The project concerns waste classification to determine if it may be recycle or not. In this approach we will use a Convolutional Neural Network(CNN) model with data augumentation to achieve a better results. It is a binary classification problem and we will train the model in Keras as well.

Data augmentation is a technique of applying different transformations to original images which results in multiple transformed copies of the same image. Each copy is different from the other ones in certain aspects depending on the augmentation techniques you apply like rotating, shifting, flipping, etc. These techniques not only expand the size of our dataset but also include a level of variation in the dataset which allows our model to generalize better on unseen data. The model becomes also more robust when it is trained on new, slightly altered images.

## Data
The dataset contains images divided into two classes: organic waste and recycle waste and it is split into train data (85%) and test data (15%). Training dataset contains 22564 images while test dataset 2513 images.

## Key Steps:

- Set up Kaggle API: Install the Kaggle API and set your API key.
- Download Kaggle Dataset: Use the Kaggle API to download the desired dataset.
- Prepare Data: Extract the downloaded dataset, organize it into training and testing directories, and apply data augmentation techniques.
- Build and Train Model: Define the model architecture, compile it, train it on the training data, and evaluate its performance on the testing data.
- Save the Model: Save the trained model for future use.

## Additional Notes:
- Execute code line by line in the Jupyter notebook.
- Visualize augmented data to understand its impact.
- Train the model until the desired accuracy is achieved.

## Machine Learning Team Member
- Muhammad Raihan Al Fuady          M742D4KY3289
- Henny Gloria Datubara             M281D4KX1863