-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 12 Kwi 2018, 21:56
-- Wersja serwera: 10.1.31-MariaDB
-- Wersja PHP: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `stratum`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `bug`
--

CREATE TABLE `bug` (
  `bug_id` bigint(20) NOT NULL,
  `user_story_id` bigint(20) DEFAULT NULL,
  `workitem_data_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `functionality`
--

CREATE TABLE `functionality` (
  `functionality_id` bigint(20) NOT NULL,
  `estimated_hours` int(11) DEFAULT NULL,
  `pending_hours` int(11) DEFAULT NULL,
  `workitem_data_id` bigint(20) DEFAULT NULL,
  `assigned_user_id` bigint(20) DEFAULT NULL,
  `requirement_id` bigint(20) DEFAULT NULL,
  `specification_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `functionality_sprint_xref`
--

CREATE TABLE `functionality_sprint_xref` (
  `functionality_sprint_xref_id` bigint(20) NOT NULL,
  `functionality_id` bigint(20) DEFAULT NULL,
  `sprint_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `project`
--

CREATE TABLE `project` (
  `project_id` bigint(20) NOT NULL,
  `project_start` bigint(20) DEFAULT NULL,
  `project_end` bigint(20) DEFAULT NULL,
  `project_name` text,
  `project_owner_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `requirement`
--

CREATE TABLE `requirement` (
  `requirement_id` bigint(20) NOT NULL,
  `workitem_data_id` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `specification`
--

CREATE TABLE `specification` (
  `specification_id` bigint(20) NOT NULL,
  `workitem_data_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `sprint`
--

CREATE TABLE `sprint` (
  `sprint_id` bigint(20) NOT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `sprint_number` int(11) DEFAULT NULL,
  `start_date` bigint(20) DEFAULT NULL,
  `end_date` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stakeholder`
--

CREATE TABLE `stakeholder` (
  `stakeholder_id` bigint(20) NOT NULL,
  `functionality_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `state`
--

CREATE TABLE `state` (
  `state_id` bigint(20) NOT NULL,
  `state_name` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `system_user`
--

CREATE TABLE `system_user` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_data_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `time_entry`
--

CREATE TABLE `time_entry` (
  `time_entry_id` bigint(20) NOT NULL,
  `hours_spent` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `functionality_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_data`
--

CREATE TABLE `user_data` (
  `user_data_id` bigint(20) NOT NULL,
  `user_login` text,
  `user_password` text,
  `user_name` text,
  `user_email` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_project_xref`
--

CREATE TABLE `user_project_xref` (
  `user_project_xref_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_role`
--

CREATE TABLE `user_role` (
  `role_id` bigint(20) NOT NULL,
  `role_name` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user_story`
--

CREATE TABLE `user_story` (
  `user_story_id` bigint(20) NOT NULL,
  `functionality_id` bigint(20) DEFAULT NULL,
  `workitem_data_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `workitem_data`
--

CREATE TABLE `workitem_data` (
  `workitem_data_id` bigint(20) NOT NULL,
  `submitter_id` bigint(20) DEFAULT NULL,
  `state_id` bigint(20) DEFAULT NULL,
  `submission_date` bigint(20) DEFAULT NULL,
  `last_modify_date` bigint(20) DEFAULT NULL,
  `workitem_title` text,
  `workitem_description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `bug`
--
ALTER TABLE `bug`
  ADD PRIMARY KEY (`bug_id`),
  ADD KEY `user_story_id` (`user_story_id`),
  ADD KEY `workitem_data_id` (`workitem_data_id`);

--
-- Indeksy dla tabeli `functionality`
--
ALTER TABLE `functionality`
  ADD PRIMARY KEY (`functionality_id`),
  ADD KEY `workitem_data_id` (`workitem_data_id`),
  ADD KEY `assigned_user_id` (`assigned_user_id`),
  ADD KEY `requirement_id` (`requirement_id`),
  ADD KEY `specification_id` (`specification_id`);

--
-- Indeksy dla tabeli `functionality_sprint_xref`
--
ALTER TABLE `functionality_sprint_xref`
  ADD PRIMARY KEY (`functionality_sprint_xref_id`),
  ADD KEY `functionality_id` (`functionality_id`),
  ADD KEY `sprint_id` (`sprint_id`);

--
-- Indeksy dla tabeli `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`),
  ADD KEY `project_owner_id` (`project_owner_id`);

--
-- Indeksy dla tabeli `requirement`
--
ALTER TABLE `requirement`
  ADD PRIMARY KEY (`requirement_id`),
  ADD KEY `workitem_data_id` (`workitem_data_id`),
  ADD KEY `project_id` (`project_id`);

--
-- Indeksy dla tabeli `specification`
--
ALTER TABLE `specification`
  ADD PRIMARY KEY (`specification_id`),
  ADD KEY `workitem_data_id` (`workitem_data_id`);

--
-- Indeksy dla tabeli `sprint`
--
ALTER TABLE `sprint`
  ADD PRIMARY KEY (`sprint_id`),
  ADD KEY `project_id` (`project_id`);

--
-- Indeksy dla tabeli `stakeholder`
--
ALTER TABLE `stakeholder`
  ADD PRIMARY KEY (`stakeholder_id`),
  ADD KEY `functionality_id` (`functionality_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeksy dla tabeli `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`state_id`);

--
-- Indeksy dla tabeli `system_user`
--
ALTER TABLE `system_user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `role_id` (`role_id`),
  ADD KEY `user_data_id` (`user_data_id`);

--
-- Indeksy dla tabeli `time_entry`
--
ALTER TABLE `time_entry`
  ADD PRIMARY KEY (`time_entry_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `functionality_id` (`functionality_id`);

--
-- Indeksy dla tabeli `user_data`
--
ALTER TABLE `user_data`
  ADD PRIMARY KEY (`user_data_id`);

--
-- Indeksy dla tabeli `user_project_xref`
--
ALTER TABLE `user_project_xref`
  ADD PRIMARY KEY (`user_project_xref_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `project_id` (`project_id`);

--
-- Indeksy dla tabeli `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indeksy dla tabeli `user_story`
--
ALTER TABLE `user_story`
  ADD PRIMARY KEY (`user_story_id`),
  ADD KEY `functionality_id` (`functionality_id`),
  ADD KEY `workitem_data_id` (`workitem_data_id`);

--
-- Indeksy dla tabeli `workitem_data`
--
ALTER TABLE `workitem_data`
  ADD PRIMARY KEY (`workitem_data_id`),
  ADD KEY `submitter_id` (`submitter_id`),
  ADD KEY `state_id` (`state_id`);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `bug`
--
ALTER TABLE `bug`
  ADD CONSTRAINT `bug_ibfk_1` FOREIGN KEY (`user_story_id`) REFERENCES `user_story` (`user_story_id`),
  ADD CONSTRAINT `bug_ibfk_2` FOREIGN KEY (`workitem_data_id`) REFERENCES `workitem_data` (`workitem_data_id`);

--
-- Ograniczenia dla tabeli `functionality`
--
ALTER TABLE `functionality`
  ADD CONSTRAINT `functionality_ibfk_1` FOREIGN KEY (`workitem_data_id`) REFERENCES `workitem_data` (`workitem_data_id`),
  ADD CONSTRAINT `functionality_ibfk_2` FOREIGN KEY (`assigned_user_id`) REFERENCES `system_user` (`user_id`),
  ADD CONSTRAINT `functionality_ibfk_3` FOREIGN KEY (`requirement_id`) REFERENCES `requirement` (`requirement_id`),
  ADD CONSTRAINT `functionality_ibfk_4` FOREIGN KEY (`specification_id`) REFERENCES `specification` (`specification_id`);

--
-- Ograniczenia dla tabeli `functionality_sprint_xref`
--
ALTER TABLE `functionality_sprint_xref`
  ADD CONSTRAINT `functionality_sprint_xref_ibfk_1` FOREIGN KEY (`functionality_id`) REFERENCES `functionality` (`functionality_id`),
  ADD CONSTRAINT `functionality_sprint_xref_ibfk_2` FOREIGN KEY (`sprint_id`) REFERENCES `sprint` (`sprint_id`);

--
-- Ograniczenia dla tabeli `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `project_ibfk_1` FOREIGN KEY (`project_owner_id`) REFERENCES `system_user` (`user_id`);

--
-- Ograniczenia dla tabeli `requirement`
--
ALTER TABLE `requirement`
  ADD CONSTRAINT `requirement_ibfk_1` FOREIGN KEY (`workitem_data_id`) REFERENCES `workitem_data` (`workitem_data_id`),
  ADD CONSTRAINT `requirement_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`);

--
-- Ograniczenia dla tabeli `specification`
--
ALTER TABLE `specification`
  ADD CONSTRAINT `specification_ibfk_1` FOREIGN KEY (`workitem_data_id`) REFERENCES `workitem_data` (`workitem_data_id`);

--
-- Ograniczenia dla tabeli `sprint`
--
ALTER TABLE `sprint`
  ADD CONSTRAINT `sprint_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`);

--
-- Ograniczenia dla tabeli `stakeholder`
--
ALTER TABLE `stakeholder`
  ADD CONSTRAINT `stakeholder_ibfk_1` FOREIGN KEY (`functionality_id`) REFERENCES `functionality` (`functionality_id`),
  ADD CONSTRAINT `stakeholder_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`user_id`);

--
-- Ograniczenia dla tabeli `system_user`
--
ALTER TABLE `system_user`
  ADD CONSTRAINT `system_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`),
  ADD CONSTRAINT `system_user_ibfk_2` FOREIGN KEY (`user_data_id`) REFERENCES `user_data` (`user_data_id`);

--
-- Ograniczenia dla tabeli `time_entry`
--
ALTER TABLE `time_entry`
  ADD CONSTRAINT `time_entry_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`user_id`),
  ADD CONSTRAINT `time_entry_ibfk_2` FOREIGN KEY (`functionality_id`) REFERENCES `functionality` (`functionality_id`);

--
-- Ograniczenia dla tabeli `user_project_xref`
--
ALTER TABLE `user_project_xref`
  ADD CONSTRAINT `user_project_xref_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`user_id`),
  ADD CONSTRAINT `user_project_xref_ibfk_2` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`);

--
-- Ograniczenia dla tabeli `user_story`
--
ALTER TABLE `user_story`
  ADD CONSTRAINT `user_story_ibfk_1` FOREIGN KEY (`functionality_id`) REFERENCES `functionality` (`functionality_id`),
  ADD CONSTRAINT `user_story_ibfk_2` FOREIGN KEY (`workitem_data_id`) REFERENCES `workitem_data` (`workitem_data_id`);

--
-- Ograniczenia dla tabeli `workitem_data`
--
ALTER TABLE `workitem_data`
  ADD CONSTRAINT `workitem_data_ibfk_1` FOREIGN KEY (`submitter_id`) REFERENCES `system_user` (`user_id`),
  ADD CONSTRAINT `workitem_data_ibfk_2` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
