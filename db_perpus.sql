-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 25 Jul 2020 pada 05.20
-- Versi Server: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_perpus`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_admin`
--

CREATE TABLE IF NOT EXISTS `tbl_admin` (
  `username` varchar(20) NOT NULL,
  `nama_admin` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `no_telepon` int(13) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_admin`
--

INSERT INTO `tbl_admin` (`username`, `nama_admin`, `password`, `alamat`, `no_telepon`, `email`) VALUES
('2', 'bara', 'bara', 'Lampung', 815, 'bara@gmail.com'),
('4', 'mijan', 'mijan', 'bantul', 857, 'mijan@gmail.com'),
('5', 'wyn', 'wyn', '', 831, 'wyn@gmail.com'),
('admin', 'admin', 'admin', 'admin', 841, 'admin'),
('wayyy', 'wayan', '12345', 'sleman', 812, '@gmail.com');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_buku`
--

CREATE TABLE IF NOT EXISTS `tbl_buku` (
  `no_buku` varchar(20) NOT NULL,
  `judul` varchar(100) NOT NULL,
  `pengarang` varchar(50) NOT NULL,
  `penerbit` varchar(100) NOT NULL,
  `kategori` varchar(100) NOT NULL,
  `tahun_terbit` int(11) NOT NULL,
  `rak` varchar(5) NOT NULL,
  `jumlah` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_buku`
--

INSERT INTO `tbl_buku` (`no_buku`, `judul`, `pengarang`, `penerbit`, `kategori`, `tahun_terbit`, `rak`, `jumlah`) VALUES
('B1', 'avangers', 'aku', 'dia', 'hiburan', 2019, '2', 3),
('B2', 'dillan', 'dia', 'aku', 'roman', 2012, '2', 7),
('B3', 'heihi', 'dia', 'aku', 'lucu bet', 4089, '5', 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_peminjaman`
--

CREATE TABLE IF NOT EXISTS `tbl_peminjaman` (
  `no_peminjaman` int(15) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `nisn` varchar(15) NOT NULL,
  `no_buku` varchar(15) NOT NULL,
  `status_pinjam` varchar(20) NOT NULL,
  `tgl_kembali` date NOT NULL,
  `denda` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_peminjaman`
--

INSERT INTO `tbl_peminjaman` (`no_peminjaman`, `tgl_pinjam`, `nisn`, `no_buku`, `status_pinjam`, `tgl_kembali`, `denda`) VALUES
(3, '1970-01-01', 'S04 - ibu', 'B1 - avangers', 'Dikembalikan', '1970-01-01', 15000),
(4, '1970-01-01', 'S01 - wayan', 'B2 - dillan', 'Dikembalikan', '1970-01-01', 15000),
(12, '1970-01-01', 'S03 - ayah', 'B1 - avangers', 'Dikembalikan', '1970-01-01', 5000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_pengembalian`
--

CREATE TABLE IF NOT EXISTS `tbl_pengembalian` (
  `no_pengembalian` int(15) NOT NULL,
  `tgl_pengembalian` int(11) NOT NULL,
  `denda` int(15) NOT NULL,
  `no_buku` int(15) NOT NULL,
  `nisn` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tbl_siswa`
--

CREATE TABLE IF NOT EXISTS `tbl_siswa` (
  `nisn` varchar(20) NOT NULL,
  `nama_siswa` varchar(50) NOT NULL,
  `kelas` varchar(10) NOT NULL,
  `no_telp` int(13) NOT NULL,
  `jen_kel` char(10) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `agama` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tbl_siswa`
--

INSERT INTO `tbl_siswa` (`nisn`, `nama_siswa`, `kelas`, `no_telp`, `jen_kel`, `alamat`, `agama`) VALUES
('S01', 'wayan', 'IX IPS II', 835, 'Laki-Laki', 'Kalianda', 'Hindu'),
('S02', 'Arab', 'XII IPA II', 878, 'Perempuan', 'sragi', 'hindu'),
('S03', 'ayah', 'hehe', 823, 'Laki-Laki', 'lampung', 'Islam'),
('S05', 'Putu', 'SI04', 871, 'Laki-Laki', 'hehe', 'Hindu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_admin`
--
ALTER TABLE `tbl_admin`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `tbl_buku`
--
ALTER TABLE `tbl_buku`
  ADD PRIMARY KEY (`no_buku`);

--
-- Indexes for table `tbl_peminjaman`
--
ALTER TABLE `tbl_peminjaman`
  ADD PRIMARY KEY (`no_peminjaman`);

--
-- Indexes for table `tbl_pengembalian`
--
ALTER TABLE `tbl_pengembalian`
  ADD PRIMARY KEY (`no_pengembalian`);

--
-- Indexes for table `tbl_siswa`
--
ALTER TABLE `tbl_siswa`
  ADD PRIMARY KEY (`nisn`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
