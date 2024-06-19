-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 17 juin 2024 à 03:13
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
  `LOCATAIRE_ID` int(11) DEFAULT NULL
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
(1, 'R plus 3', '106 zone e captage', '4 etages meubles', 'climatiseur et eau chaude', 'immeuble1.jpg\r\n'),
(2, 'immeuble 2 ', 'cornche', '4 etages meubles', 'climatiseur et eau chaude et jardin', 'immeuble1.jpg\r\n\r\n'),
(3, 'R plus 5 ', '106 hlm 5', '5 etages meubles', 'climatiseur et eau chaude', 'immeuble1.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `locataire`
--

CREATE TABLE `locataire` (
  `ID` int(11) NOT NULL,
  `NOM` varchar(255) NOT NULL,
  `EMAIL` varchar(255) NOT NULL,
  `MOT_DE_PASSE` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(1, 'rez de chausse', 5, 120.00, 150000.00, 1, 'Imm1Loc1.jpg', NULL),
(2, '1er etage', 5, 120.00, 150000.00, 1, 'Imm1Loc2.jpg', NULL),
(3, '2e etage', 5, 120.00, 150000.00, 1, 'Imm1Loc3.jpg', NULL),
(4, '3e etage', 4, 120.00, 170000.00, 1, 'Imm1Loc4.jpg', NULL);

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
-- Index pour la table `immeuble`
--
ALTER TABLE `immeuble`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `locataire`
--
ALTER TABLE `locataire`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`);

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
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `immeuble`
--
ALTER TABLE `immeuble`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `locataire`
--
ALTER TABLE `locataire`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `paiement`
--
ALTER TABLE `paiement`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `unitelocation`
--
ALTER TABLE `unitelocation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

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
