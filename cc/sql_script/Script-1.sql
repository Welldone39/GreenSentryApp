create table action_list(
	id serial not null,
	author varchar(100) not null,
	news_source varchar(100) not null,
	title varchar(250) not null,
	description varchar(500) not null,
	url varchar(250) not null,
	url_to_image varchar(500) not null,
	primary key(id)
);


-- 1
insert into action_list (author, news_source, title, description, url, url_to_image)
values
(
	'Lemonilo',
	'lemonilo.com',
	'Pandawara Group, Lima Anak Muda Pendekar Lingkungan yang Dikenal Lewat Aksi Bersih Sungai',
	'Tumpukan sampah di bibir sungai dan selokan membuat lima sekawan asal Bandung tergerak untuk terjun langsung membersihkan sampah tersebut. Bermodalkan alat kebersihan seadanya, Pandawara Group bergotong royong membersihkan sampah agar aliran selokan bisa kembali lancar. Aksi lima sekawan yang diunggah ke akun TikTok pribadi mereka pun menyita perhatian warganet. Ingin tahu lebih lanjut tentang Pandawara Group? Yuk, simak artikel berikut!',
	'https://lemonilo.com/pandawara-group-lima-anak-muda-pendekar-lingkungan-yang-dikenal-lewat-aksi-bersih-sungai/',
	'https://lemonilo.com/wp-content/uploads/2024/01/63cf8a14685265000188d51c-1024x683.png'
);

-- 2
insert into action_list (author, news_source, title, description, url, url_to_image)
values
(
	'Amilia Turosida',
	'www.goodnewsfromindonesia.id',
	'Perjuangan Pandawara Group: Habiskan Satu Juta untuk Bersihkan Satu Sungai',
	'Sampah adalah salah satu hal yang tidak bisa lepas dari aktivitas manusia dalam kehidupan sehari-hari. Setiap hari suatu barang diproduksi dan dipakai oleh manusia, yang tak lama kemudian barang habis pakai itu bertumpuk menjadi tumpukan sampah. Seperti yang disampaikan oleh Kementerian Lingkungan Hidup dan Kehutanan,175.000 ton sampah terkumpul setiap harinya. Yang artinya, setiap penduduk warga negara Indonesia menyumbang sekitar 0,7 kg sampah per hari.',
	'https://www.goodnewsfromindonesia.id/2023/10/12/perjuangan-pandawara-group-bersihkan-sungai',
	'https://cdn-u1-gnfi.imgix.net/post/large-img-20231012-195647-418648355ef39cd2df22aa3e21073002.jpg?fit=crop&crop=faces%2Centropy&lossless=true&auto=compress%2Cformat&w=730&h=486'
);

--- Done -----

-- 3
insert into action_list (author, news_source, title, description, url, url_to_image)
values
(
	'MERDEKA.COM',
	'www.merdeka.com',
	'Dampak Buang Sampah Sembarangan bagi Lingkungan, Wajib Diperhatikan',
	'Sampah menjadi salah satu masalah lingkungan yang sampai saat ini masih sulit diatasi. Kita sebagai manusia adalah penghasil sampah. Namun, yang jadi masalah bukanlah sampahnya, melainkan perilaku orang yang membuang sampah tersebut tidak pada tempatnya.',
	'https://www.merdeka.com/jabar/dampak-buang-sampah-sembarangan-bagi-lingkungan-wajib-diperhatikan-kln.html',
	'https://cdns.klimg.com/merdeka.com/i/w/news/2021/12/28/1391614/540x270/dampak-buang-sampah-sembarangan-bagi-lingkungan-wajib-diperhatikan.jpg'
);

-- 4
insert into action_list (author, news_source, title, description, url, url_to_image)
values
(
	'Elok Nuri',
	'narasi.tv',
	'Pandawara Group: Aksi Anak Muda Bersihkan Sampah yang Viral di TikTok',
	'Baru-baru ini aksi anak muda yang tergabung dalam Pandawara Group menyita perhatian publik terutama di jagat media sosial. Hal tersebut terjadi karena Pandawara Group rutin membagikan video aksi bersih-bersih sampah di sungai. Berkat aksi bersih-bersih sungai tersebut, akun @pandawaragroup telah mendapatkan 6,4 juta pengikut dan 91,4 juta like di TikTok. Lantas siapa sebenarnya Pandawara Group? Aksi apa saja yang telah mereka lakukan?',
	'https://narasi.tv/read/narasi-daily/siapa-pandawara-group',
	'https://images.narasi.tv/preset:sharp/resize:fill:877:489:1/gravity:ce/plain/https://storage.googleapis.com/narasi-production.appspot.com/production/medium/1684145978322/pandawara-group-aksi-anak-muda-bersihkan-sampah-yang-viral-di-tiktok-medium.webp@webp'
);

-- 5
insert into action_list (author, news_source, title, description, url, url_to_image)
values
(
	'Debora Laksmi Indraswari',
	'www.kompas.id',
	'Menumbuhkan Kesadaran Mengelola Sampah melalui Bank Sampah',
	'Untuk menumbuhkan kesadaran dalam mengolah sampah rumah tangga, perlu adanya insentif yang bermanfaat secara langsung bagi masyarakat. Bank sampah yang sudah diterapkan di sejumlah daerah di Indonesia merupakan salah satu bentuk manfaat langsung yang dapat diimplementasikan dalam kehidupan masyarakat sehari-hari.',
	'https://www.kompas.id/baca/riset/2023/08/12/menumbuhkan-kesadaran-mengelola-sampah-melalui-bank-sampah',
	'https://asset.kgnewsroom.com/photo/pre/2023/07/28/8f1e3130-56ff-4e91-890f-7f8797ae350c_jpg.jpg'
);

-- 6
insert into action_list (author, news_source, title, description, url, url_to_image)
values
(
	'Rilis Humas Pemdakab Garut',
	'jabarprov.go.id',
	'Aksi Pemuda Garut Berseka Bersihkan Sungai Citameng dari Tumpukan Sampah',
	'Sejumlah anak muda yang tergabung dalam komunitas "Garut Berseka" di Kabupaten Garut melakukan aksi bersih-bersih di Sungai Citameng, Kampung Gadog, Kabupaten Garut, Minggu (19/11/2023).',
	'https://jabarprov.go.id/berita/aksi-pemuda-garut-berseka-bersihkan-sungai-citameng-dari-tumpukan-sampah-11406',
	'https://dvgddkosknh6r.cloudfront.net/live/media/img/1700464146-WhatsApp-Image-2023-11-19-at-20.06.56.jpeg'
);

-- 7
insert into action_list (author, news_source, title, description, url, url_to_image)
values
(
	'Serafica Gischa',
	'www.kompas.com',
	'Jenis-jenis Sampah berdasarkan Klasifikasinya',
	'Sampah bisa berasal dari mana saja selama terdapat aktivitas kehidupan di daerah tersebut. Sampah bisa berasal dari rumah tangga, pasar, jalanan, fasilitas umum (terminal, stasiun, bandara), dan lain sebagainya.',
	'https://www.kompas.com/skola/read/2023/03/14/220000169/jenis-jenis-sampah-berdasarkan-klasifikasinya?page=all',
	'https://asset.kompas.com/crops/Z8uxUAXrbYiswLilKw626FkfsFw=/0x21:1440x981/750x500/data/photo/2023/03/14/641023d85f308.png'
);

-- 8
insert into action_list (author, news_source, title, description, url, url_to_image)
values
(
	'Kominfo',
	'www.kominfo.go.id',
	'Aksi Bersih Sampah dari Pantai Hingga ke Gunung',
	'Guna  mendukung Gerakan Tiga Bulan Bersih Sampah, masyarakat bersama sejumlah Unit Pelaksana Teknis (UPT) Direktorat Jenderal Konservasi Sumber Daya Alam dan Ekosistem (KSDAE), melakukan aksi bersih sampah beberapa waktu lalu.  Sebagaimana aksi bersih sampah yang dilakukan para para komunitas dan pelajar, bersama Balai Taman Nasional (TN) Taka Bonerate, di sekitar pantai Kepulauan Selayar, Provinsi Sulawesi Selatan.',
	'https://www.kominfo.go.id/content/detail/12674/aksi-bersih-sampah-dari-pantai-hingga-ke-gunung/0/berita',
	'https://web.kominfo.go.id/sites/default/files/Aksi%20Bersih%20Sampah%20dari%20Pantai%20Hingga%20ke%20Gunung.jpg'
);

-- 9
insert into action_list (author, news_source, title, description, url, url_to_image)
values
(
	'Kumparan',
	'kumparan.com',
	'Pengertian dan Contoh Metode 3R: Reduce, Reuse, Recycle',
	'Usaha yang dapat dilakukan untuk mengurangi jumlah sampah adalah 3R, yaitu reduce, reuse, dan recycle. Apa yang dimaksud reuse, reduce, recycle? Inilah pengertian dan contoh metode 3R yang mungkin dapat membantu Anda dalam pelestarian lingkungan.',
	'https://kumparan.com/berita-terkini/pengertian-dan-contoh-metode-3r-reduce-reuse-recycle-1ze7HrUVZR1',
	'https://blue.kumparan.com/image/upload/fl_progressive,fl_lossy,c_fill,q_auto:best,w_640/v1634025439/01gpvrferr4tr36qvpjrh74dzv.jpg'
);

-- 10
insert into action_list (author, news_source, title, description, url, url_to_image)
values
(
	'Dyah Ayu Pamela',
	'www.liputan6.com',
	'Aksi Sekelompok Pemuda Bersihkan Sampah di Sungai Viral di TikTok',
	'Sebuah video di TikTok tengah viral, namun bukan berisi konten humor atau kelakuan nyeleneh warganet. Tayangan itu merupakan aksi mengurangi sampah yang dilakukan oleh sekelompok pemuda, yang menamakan dirinya Pandawara. ',
	'https://www.liputan6.com/lifestyle/read/5096574/aksi-sekelompok-pemuda-bersihkan-sampah-di-sungai-viral-di-tiktok',
	'https://cdn1-production-images-kly.akamaized.net/Wx_0lx4y9FwRjzO96WdFjleUiG0=/1280x720/smart/filters:quality(75):strip_icc():format(webp)/kly-media-production/medias/4190910/original/040156900_1665662640-WhatsApp_Image_2022-10-13_at_18.59.40.jpeg'
);

---------------------------------------------------------------------------
-- Creating Users table --

create table users(
	id varchar(100) not null,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	email varchar(100) not null,
	hashed_password varchar(100) not null,
	primary key (id)
);

-- create index for id and email column on users table
create index id_email_index on users(id, email);
