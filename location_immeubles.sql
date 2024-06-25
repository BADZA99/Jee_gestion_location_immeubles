-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 25 juin 2024 à 15:00
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `location_immeubles`
--

-- --------------------------------------------------------

--
-- Structure de la table `contratlocation`
--

CREATE TABLE `contratlocation` (
  `ID` int(11) NOT NULL,
  `DATE_DEBUT` date NOT NULL,
  `DATE_FIN` date NOT NULL,
  `UNITE_LOCATION_ID` int(11) DEFAULT NULL,
  `LOCATAIRE_ID` int(11) DEFAULT NULL,
  `Statut` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `contratlocation`
--

INSERT INTO `contratlocation` (`ID`, `DATE_DEBUT`, `DATE_FIN`, `UNITE_LOCATION_ID`, `LOCATAIRE_ID`, `Statut`) VALUES
(1, '2024-06-22', '2024-12-22', 2, 1, 'pending'),
(2, '2024-06-22', '2024-12-22', 1, 1, 'pending'),
(3, '2024-06-22', '2024-12-22', 3, 1, 'pending');

-- --------------------------------------------------------

--
-- Structure de la table `demandelocation`
--

CREATE TABLE `demandelocation` (
  `ID` int(11) NOT NULL,
  `DATE_DEMANDE` date NOT NULL,
  `STATUT` enum('EN_ATTENTE','ACCEPTEE','REFUSEE') NOT NULL DEFAULT 'EN_ATTENTE',
  `LOCATAIRE_ID` int(11) NOT NULL,
  `UNITE_LOCATION_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `immeuble`
--

CREATE TABLE `immeuble` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `ADRESSE` varchar(255) NOT NULL,
  `DESCRIPTION` text DEFAULT NULL,
  `EQUIPEMENTS` varchar(255) DEFAULT NULL,
  `IMAGE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `immeuble`
--

INSERT INTO `immeuble` (`ID`, `NOM`, `ADRESSE`, `DESCRIPTION`, `EQUIPEMENTS`, `IMAGE`) VALUES
(1, 'R plus 3Z', '106 zone e captage', '4 etages meubles', 'climatiseur et eau chaude', 'immeuble1.jpg'),
(3, 'R plus 5 ', '106 hlm 5', '5 etages meubles', 'climatiseur et eau chaude', 'immeuble1.jpg'),
(6, 'badara', 'SHIBUYAaaa', 'projet second semestre l3gl', 'clim', 'MINIA DANGEREOUS.jpg'),
(8, 'papa', 'SHIBUYAaaa', 'ceci est mon projet de fin annee en java avec jee ', 'clim', 'slider-1.jpg'),
(9, 'papabnsq', 'dakarr', 'projet licence applicatione commerce pshop front avec vite ', 'clim', 'patient1.png');

-- --------------------------------------------------------

--
-- Structure de la table `locataire`
--

CREATE TABLE `locataire` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `MOT_DE_PASSE` varchar(255) NOT NULL,
  `ID_UTILISATEUR` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `locataire`
--

INSERT INTO `locataire` (`ID`, `NOM`, `EMAIL`, `MOT_DE_PASSE`, `ID_UTILISATEUR`) VALUES
(1, 'deveaa', '', 'passera', 16),
(11, 'dddddd', '33@locataire.com', 'dddddddddd', 33);

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement` (
  `ID` int(11) NOT NULL,
  `MONTANT` decimal(10,2) NOT NULL,
  `DATE_PAIEMENT` date NOT NULL,
  `CONTRAT_LOCATION_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `unitelocation`
--

CREATE TABLE `unitelocation` (
  `ID` int(11) NOT NULL,
  `NUMERO_UNITE` varchar(255) NOT NULL,
  `NOMBRE_PIECES` int(11) DEFAULT NULL,
  `SUPERFICIE` decimal(10,2) DEFAULT NULL,
  `LOYER` decimal(10,2) DEFAULT NULL,
  `IMMEUBLE_ID` int(11) DEFAULT NULL,
  `IMAGE` varchar(255) DEFAULT NULL,
  `TARIF_LOCATION` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `unitelocation`
--

INSERT INTO `unitelocation` (`ID`, `NUMERO_UNITE`, `NOMBRE_PIECES`, `SUPERFICIE`, `LOYER`, `IMMEUBLE_ID`, `IMAGE`, `TARIF_LOCATION`) VALUES
(1, 'rez de chaussez', 5, 120.00, 150000.00, 1, 'Imm1Loc1.jpg', NULL),
(2, '1er etage', 5, 120.00, 150000.00, 1, 'Imm1Loc2.jpg', NULL),
(3, '2e etage', 7, 120.00, 2230000.00, 1, 'Imm1Loc3.jpg', NULL),
(5, 'dfdfdfdfdfdfdfdfd', 7, 123.00, 150000.00, 3, 'test.jpg', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `ID` int(11) NOT NULL,
  `NOM_UTILISATEUR` varchar(255) NOT NULL,
  `MOT_DE_PASSE` varchar(255) NOT NULL,
  `ROLE` enum('ADMIN','PROPRIETAIRE','LOCATAIRE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`ID`, `NOM_UTILISATEUR`, `MOT_DE_PASSE`, `ROLE`) VALUES
(1, 'admin', 'PASSERA', 'ADMIN'),
(16, 'deveaa', 'passera', 'LOCATAIRE'),
(19, 'proprio', 'passer', 'PROPRIETAIRE'),
(20, 'ndiaye', 'passera', 'ADMIN'),
(21, 'papabn', 'passer', 'LOCATAIRE'),
(23, 'test', 'passer', 'LOCATAIRE'),
(24, 'modou', 'passer', 'LOCATAIRE'),
(25, 'ssssss', 'ssssssssssss', 'LOCATAIRE'),
(26, 'sssss', 'sssssss', 'PROPRIETAIRE'),
(27, 'ffffff', 'fffff', 'LOCATAIRE'),
(28, 'vvvvvv', 'vvvvvvvvvvv', 'LOCATAIRE'),
(29, 'sssssssss', 'ssssssssssssss', 'LOCATAIRE'),
(31, 'qqqqqqq', 'qqqqqqqqqqqqq', 'LOCATAIRE'),
(32, 'zzzzzzzzzf', 'fffffffffffff', 'LOCATAIRE'),
(33, 'dddddd', 'dddddddddd', 'LOCATAIRE');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `contratlocation`
--
ALTER TABLE `contratlocation`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK4er5ekvwrcamw9c8p10vpwses` (`LOCATAIRE_ID`),
  ADD KEY `FKq3tel545g6xj28oveuhq98sx1` (`UNITE_LOCATION_ID`);

--
-- Index pour la table `demandelocation`
--
ALTER TABLE `demandelocation`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `immeuble`
--
ALTER TABLE `immeuble`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `locataire`
--
ALTER TABLE `locataire`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`),
  ADD KEY `fk_idutilisateur` (`ID_UTILISATEUR`);

--
-- Index pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FKj4bh9xta0u8x4sq73ync1gjl6` (`CONTRAT_LOCATION_ID`);

--
-- Index pour la table `unitelocation`
--
ALTER TABLE `unitelocation`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FKsksma4kwra6gypf11exw08whn` (`IMMEUBLE_ID`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NOM_UTILISATEUR` (`NOM_UTILISATEUR`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `contratlocation`
--
ALTER TABLE `contratlocation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `demandelocation`
--
ALTER TABLE `demandelocation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `immeuble`
--
ALTER TABLE `immeuble`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `locataire`
--
ALTER TABLE `locataire`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `paiement`
--
ALTER TABLE `paiement`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `unitelocation`
--
ALTER TABLE `unitelocation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `contratlocation`
--
ALTER TABLE `contratlocation`
  ADD CONSTRAINT `FK4er5ekvwrcamw9c8p10vpwses` FOREIGN KEY (`LOCATAIRE_ID`) REFERENCES `locataire` (`ID`),
  ADD CONSTRAINT `FKq3tel545g6xj28oveuhq98sx1` FOREIGN KEY (`UNITE_LOCATION_ID`) REFERENCES `unitelocation` (`ID`),
  ADD CONSTRAINT `contratlocation_ibfk_1` FOREIGN KEY (`UNITE_LOCATION_ID`) REFERENCES `unitelocation` (`ID`),
  ADD CONSTRAINT `contratlocation_ibfk_2` FOREIGN KEY (`LOCATAIRE_ID`) REFERENCES `locataire` (`ID`);

--
-- Contraintes pour la table `locataire`
--
ALTER TABLE `locataire`
  ADD CONSTRAINT `fk_idutilisateur` FOREIGN KEY (`ID_UTILISATEUR`) REFERENCES `utilisateur` (`ID`);

--
-- Contraintes pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `FKj4bh9xta0u8x4sq73ync1gjl6` FOREIGN KEY (`CONTRAT_LOCATION_ID`) REFERENCES `contratlocation` (`ID`),
  ADD CONSTRAINT `paiement_ibfk_1` FOREIGN KEY (`CONTRAT_LOCATION_ID`) REFERENCES `contratlocation` (`ID`);

--
-- Contraintes pour la table `unitelocation`
--
ALTER TABLE `unitelocation`
  ADD CONSTRAINT `FKsksma4kwra6gypf11exw08whn` FOREIGN KEY (`IMMEUBLE_ID`) REFERENCES `immeuble` (`ID`),
  ADD CONSTRAINT `unitelocation_ibfk_1` FOREIGN KEY (`IMMEUBLE_ID`) REFERENCES `immeuble` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
