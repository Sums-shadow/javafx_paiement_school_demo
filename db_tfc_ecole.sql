-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : Dim 19 sep. 2021 à 20:57
-- Version du serveur :  5.7.24
-- Version de PHP : 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_tfc_ecole`
--

-- --------------------------------------------------------

--
-- Structure de la table `t_auth`
--

CREATE TABLE `t_auth` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `t_auth`
--

INSERT INTO `t_auth` (`id`, `email`, `password`) VALUES
(1, 'shadow@gmail.com', 'azerty');

-- --------------------------------------------------------

--
-- Structure de la table `t_eleve`
--

CREATE TABLE `t_eleve` (
  `mat` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `sexe` varchar(1) NOT NULL,
  `classe` int(11) NOT NULL,
  `niveau` int(11) NOT NULL,
  `section` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `t_eleve`
--

INSERT INTO `t_eleve` (`mat`, `nom`, `prenom`, `sexe`, `classe`, `niveau`, `section`) VALUES
(1, 'Makunda', 'Daniel', 'M', 4, 3, 4),
(2, 'Mutombo', 'Fabrice', 'M', 5, 1, NULL),
(3, 'Mwamba', 'Eunice', 'F', 6, 3, 1),
(4, 'Ngbowa', 'Ezechiel', 'M', 6, 3, 3),
(5, 'Basilua', 'Jaspe', 'F', 2, 2, NULL),
(6, 'Isamene', 'Jason', 'M', 1, 2, NULL),
(7, 'Lola', 'Dan', 'M', 4, 1, NULL),
(8, 'Mulongo', 'Yves', 'M', 1, 2, NULL),
(9, 'Kajibale', 'Jovial', 'M', 1, 1, NULL),
(10, 'Kalenga', 'Axel', 'M', 5, 3, 2),
(11, 'Doe', 'John', 'M', 5, 3, 3);

-- --------------------------------------------------------

--
-- Structure de la table `t_niveau`
--

CREATE TABLE `t_niveau` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `frais` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `t_niveau`
--

INSERT INTO `t_niveau` (`id`, `titre`, `frais`) VALUES
(1, 'Primaire', 150),
(2, 'Secondaire', 250),
(3, 'Humanité', 320);

-- --------------------------------------------------------

--
-- Structure de la table `t_paiement`
--

CREATE TABLE `t_paiement` (
  `id` int(11) NOT NULL,
  `mat` int(11) NOT NULL,
  `montant` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `t_section`
--

CREATE TABLE `t_section` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `t_section`
--

INSERT INTO `t_section` (`id`, `titre`) VALUES
(1, 'Litteraire'),
(2, 'Pedagogie'),
(3, 'Scientifique'),
(4, 'Commercial');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `v_eleve`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `v_eleve` (
`mat_eleve` int(11)
,`nom` varchar(255)
,`prenom` varchar(255)
,`sexe` varchar(1)
,`classe` int(11)
,`titre_niveau` varchar(255)
,`id_section` int(11)
,`frais_payer` int(11)
);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `v_paiement`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `v_paiement` (
`mat_eleve` int(11)
,`nom_eleve` varchar(255)
,`prenom_eleve` varchar(255)
,`sexe_eleve` varchar(1)
,`classe` int(11)
,`id_niveau` int(11)
,`titre_niveau` varchar(255)
,`frais_payer` int(11)
,`id_section` int(11)
,`montant_payer` int(11)
,`date_payer` timestamp
);

-- --------------------------------------------------------

--
-- Structure de la vue `v_eleve`
--
DROP TABLE IF EXISTS `v_eleve`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_eleve`  AS  (select `t_eleve`.`mat` AS `mat_eleve`,`t_eleve`.`nom` AS `nom`,`t_eleve`.`prenom` AS `prenom`,`t_eleve`.`sexe` AS `sexe`,`t_eleve`.`classe` AS `classe`,`t_niveau`.`titre` AS `titre_niveau`,`t_eleve`.`section` AS `id_section`,`t_niveau`.`frais` AS `frais_payer` from (`t_eleve` join `t_niveau`) where (`t_eleve`.`niveau` = `t_niveau`.`id`)) ;

-- --------------------------------------------------------

--
-- Structure de la vue `v_paiement`
--
DROP TABLE IF EXISTS `v_paiement`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_paiement`  AS  (select `t_eleve`.`mat` AS `mat_eleve`,`t_eleve`.`nom` AS `nom_eleve`,`t_eleve`.`prenom` AS `prenom_eleve`,`t_eleve`.`sexe` AS `sexe_eleve`,`t_eleve`.`classe` AS `classe`,`t_eleve`.`niveau` AS `id_niveau`,`t_niveau`.`titre` AS `titre_niveau`,`t_niveau`.`frais` AS `frais_payer`,`t_eleve`.`section` AS `id_section`,`t_paiement`.`montant` AS `montant_payer`,`t_paiement`.`date` AS `date_payer` from ((`t_eleve` join `t_paiement`) join `t_niveau`) where ((`t_paiement`.`mat` = `t_eleve`.`mat`) and (`t_eleve`.`niveau` = `t_niveau`.`id`))) ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `t_auth`
--
ALTER TABLE `t_auth`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `t_eleve`
--
ALTER TABLE `t_eleve`
  ADD PRIMARY KEY (`mat`),
  ADD KEY `niveau` (`niveau`),
  ADD KEY `section` (`section`);

--
-- Index pour la table `t_niveau`
--
ALTER TABLE `t_niveau`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `t_paiement`
--
ALTER TABLE `t_paiement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `mat` (`mat`);

--
-- Index pour la table `t_section`
--
ALTER TABLE `t_section`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `t_auth`
--
ALTER TABLE `t_auth`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `t_eleve`
--
ALTER TABLE `t_eleve`
  MODIFY `mat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `t_niveau`
--
ALTER TABLE `t_niveau`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `t_paiement`
--
ALTER TABLE `t_paiement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `t_section`
--
ALTER TABLE `t_section`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `t_eleve`
--
ALTER TABLE `t_eleve`
  ADD CONSTRAINT `t_eleve_ibfk_1` FOREIGN KEY (`niveau`) REFERENCES `t_niveau` (`id`),
  ADD CONSTRAINT `t_eleve_ibfk_2` FOREIGN KEY (`section`) REFERENCES `t_section` (`id`);

--
-- Contraintes pour la table `t_paiement`
--
ALTER TABLE `t_paiement`
  ADD CONSTRAINT `t_paiement_ibfk_1` FOREIGN KEY (`mat`) REFERENCES `t_eleve` (`mat`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
