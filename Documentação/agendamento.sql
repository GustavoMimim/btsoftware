-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           10.1.37-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              10.0.0.5460
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para agendamento
CREATE DATABASE IF NOT EXISTS `agendamento` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `agendamento`;

-- Copiando estrutura para tabela agendamento.agendamento
CREATE TABLE IF NOT EXISTS `agendamento` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `buscarAnimal` int(11) NOT NULL,
  `dataAtendimento` date NOT NULL,
  `entregarAnimal` int(11) NOT NULL,
  `horario` time NOT NULL,
  `pacote` int(11) NOT NULL,
  `codAnimal_codigo` bigint(20) NOT NULL,
  `codUsuarioInclusao_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_bjk48gd9xlrcdsfpq2yvlgoo3` (`codAnimal_codigo`),
  KEY `FK_73vo9ofjpi1ekfqhjr2r1uf4m` (`codUsuarioInclusao_codigo`),
  CONSTRAINT `FK_73vo9ofjpi1ekfqhjr2r1uf4m` FOREIGN KEY (`codUsuarioInclusao_codigo`) REFERENCES `usuario` (`codigo`),
  CONSTRAINT `FK_bjk48gd9xlrcdsfpq2yvlgoo3` FOREIGN KEY (`codAnimal_codigo`) REFERENCES `animal` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela agendamento.agendamento: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `agendamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `agendamento` ENABLE KEYS */;

-- Copiando estrutura para tabela agendamento.agendamentoservico
CREATE TABLE IF NOT EXISTS `agendamentoservico` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `preco` double DEFAULT NULL,
  `codAgendamento_codigo` bigint(20) NOT NULL,
  `codServico_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_ss5002mpstljxhrtjombyhay8` (`codAgendamento_codigo`),
  KEY `FK_5hphh69pb7wwarnsllk3tymr9` (`codServico_codigo`),
  CONSTRAINT `FK_5hphh69pb7wwarnsllk3tymr9` FOREIGN KEY (`codServico_codigo`) REFERENCES `servico` (`codigo`),
  CONSTRAINT `FK_ss5002mpstljxhrtjombyhay8` FOREIGN KEY (`codAgendamento_codigo`) REFERENCES `agendamento` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela agendamento.agendamentoservico: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `agendamentoservico` DISABLE KEYS */;
/*!40000 ALTER TABLE `agendamentoservico` ENABLE KEYS */;

-- Copiando estrutura para tabela agendamento.animal
CREATE TABLE IF NOT EXISTS `animal` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `cor` varchar(30) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `observacoes` varchar(60) DEFAULT NULL,
  `pelagem` varchar(15) NOT NULL,
  `porte` varchar(10) NOT NULL,
  `raca` varchar(30) NOT NULL,
  `sexo` varchar(5) NOT NULL,
  `codCliente_codigo` bigint(20) NOT NULL,
  `codUsuarioInclusao_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_27p6huldcphov8a290s1m704n` (`codCliente_codigo`),
  KEY `FK_rwjq1wcv40egff0vqd1kc78us` (`codUsuarioInclusao_codigo`),
  CONSTRAINT `FK_27p6huldcphov8a290s1m704n` FOREIGN KEY (`codCliente_codigo`) REFERENCES `cliente` (`codigo`),
  CONSTRAINT `FK_rwjq1wcv40egff0vqd1kc78us` FOREIGN KEY (`codUsuarioInclusao_codigo`) REFERENCES `usuario` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela agendamento.animal: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` (`codigo`, `cor`, `nome`, `observacoes`, `pelagem`, `porte`, `raca`, `sexo`, `codCliente_codigo`, `codUsuarioInclusao_codigo`) VALUES
	(1, 'marrom', 'bob', 'parara', 'alta', 'gigante', 'foda', 'M', 1, 1),
	(3, 'marrom', 'Shary', NULL, 'baixa', 'Pequeno', 'pinscher', 'F', 1, 1),
	(4, 'black', 'auau', NULL, 'media', 'Médio', 'vira', 'M', 1, 1);
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;

-- Copiando estrutura para tabela agendamento.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(30) NOT NULL,
  `cep` varchar(14) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `complemento` varchar(14) DEFAULT NULL,
  `cpf` varchar(14) NOT NULL,
  `dataAlteracao` datetime DEFAULT NULL,
  `dataInclusao` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dataNascimento` date DEFAULT NULL,
  `estado` varchar(20) NOT NULL,
  `logradouro` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `numero` int(11) NOT NULL,
  `rg` varchar(12) NOT NULL,
  `status` varchar(10) NOT NULL,
  `telefone` varchar(14) NOT NULL,
  `codUsuarioAlteracao_codigo` bigint(20) DEFAULT NULL,
  `codUsuarioInclusao_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_2l3ic39ogmd5kjadcncbsm103` (`codUsuarioAlteracao_codigo`),
  KEY `FK_4vckri5yedmw6lm77oc8ekija` (`codUsuarioInclusao_codigo`),
  CONSTRAINT `FK_2l3ic39ogmd5kjadcncbsm103` FOREIGN KEY (`codUsuarioAlteracao_codigo`) REFERENCES `usuario` (`codigo`),
  CONSTRAINT `FK_4vckri5yedmw6lm77oc8ekija` FOREIGN KEY (`codUsuarioInclusao_codigo`) REFERENCES `usuario` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela agendamento.cliente: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`codigo`, `bairro`, `cep`, `cidade`, `complemento`, `cpf`, `dataAlteracao`, `dataInclusao`, `dataNascimento`, `estado`, `logradouro`, `nome`, `numero`, `rg`, `status`, `telefone`, `codUsuarioAlteracao_codigo`, `codUsuarioInclusao_codigo`) VALUES
	(1, 'centro', '1886000', 'timbui', NULL, '1231231232', NULL, '2019-04-17 21:54:08', '2010-04-17', '', 'rua pedro', 'jose antonio', 48, '585511111', 'Ativo', '149954123', NULL, 1),
	(2, 'centro', '18860-000', 'Timburi', NULL, '48179506843', NULL, '2019-04-18 13:18:31', '2019-04-17', 'Paraná', 'pedro ribeiro', 'lucas', 22, '58511176', 'Ativo', '996217171', NULL, NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;

-- Copiando estrutura para tabela agendamento.pagamento
CREATE TABLE IF NOT EXISTS `pagamento` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataPagamento` time NOT NULL,
  `tipoPagamento` varchar(45) DEFAULT NULL,
  `valor` double NOT NULL,
  `codUsuarioInclusao_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_3mmlsnw1kvq1ulfaarxtog9j8` (`codUsuarioInclusao_codigo`),
  CONSTRAINT `FK_3mmlsnw1kvq1ulfaarxtog9j8` FOREIGN KEY (`codUsuarioInclusao_codigo`) REFERENCES `usuario` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela agendamento.pagamento: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;

-- Copiando estrutura para tabela agendamento.pagamentoservico
CREATE TABLE IF NOT EXISTS `pagamentoservico` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `codAgendamento_codigo` bigint(20) DEFAULT NULL,
  `codPagamento_codigo` bigint(20) NOT NULL,
  `codPlano_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_rlq8lfs08k66p2pdbydiv771g` (`codAgendamento_codigo`),
  KEY `FK_nimwjsxdbb0ltsko2h7qj3fbv` (`codPagamento_codigo`),
  KEY `FK_bx2arcgeyovegjr66vyx64otg` (`codPlano_codigo`),
  CONSTRAINT `FK_bx2arcgeyovegjr66vyx64otg` FOREIGN KEY (`codPlano_codigo`) REFERENCES `planoagendamento` (`codigo`),
  CONSTRAINT `FK_nimwjsxdbb0ltsko2h7qj3fbv` FOREIGN KEY (`codPagamento_codigo`) REFERENCES `pagamento` (`codigo`),
  CONSTRAINT `FK_rlq8lfs08k66p2pdbydiv771g` FOREIGN KEY (`codAgendamento_codigo`) REFERENCES `agendamento` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela agendamento.pagamentoservico: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `pagamentoservico` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamentoservico` ENABLE KEYS */;

-- Copiando estrutura para tabela agendamento.planoagendamento
CREATE TABLE IF NOT EXISTS `planoagendamento` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataCadastro` time NOT NULL,
  `dataCancelamento` time NOT NULL,
  `frequenciaAtendimento` int(11) NOT NULL,
  `frequenciaPagamento` int(11) NOT NULL,
  `horario` time NOT NULL,
  `precoPlano` double NOT NULL,
  `codAnimal_codigo` bigint(20) NOT NULL,
  `codUsuarioInclusao_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_mnb0qwxyoj74qs8wu74bcyljo` (`codAnimal_codigo`),
  KEY `FK_7fb3i341dfg1gh708rb9japss` (`codUsuarioInclusao_codigo`),
  CONSTRAINT `FK_7fb3i341dfg1gh708rb9japss` FOREIGN KEY (`codUsuarioInclusao_codigo`) REFERENCES `usuario` (`codigo`),
  CONSTRAINT `FK_mnb0qwxyoj74qs8wu74bcyljo` FOREIGN KEY (`codAnimal_codigo`) REFERENCES `animal` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela agendamento.planoagendamento: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `planoagendamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `planoagendamento` ENABLE KEYS */;

-- Copiando estrutura para tabela agendamento.planoservico
CREATE TABLE IF NOT EXISTS `planoservico` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `codPlano_codigo` bigint(20) NOT NULL,
  `codServico_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_etlnlgkacsho1p2q5t67yvb2` (`codPlano_codigo`),
  KEY `FK_retrpq0y1548xyrvx6t6cyd82` (`codServico_codigo`),
  CONSTRAINT `FK_etlnlgkacsho1p2q5t67yvb2` FOREIGN KEY (`codPlano_codigo`) REFERENCES `planoagendamento` (`codigo`),
  CONSTRAINT `FK_retrpq0y1548xyrvx6t6cyd82` FOREIGN KEY (`codServico_codigo`) REFERENCES `servico` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela agendamento.planoservico: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `planoservico` DISABLE KEYS */;
/*!40000 ALTER TABLE `planoservico` ENABLE KEYS */;

-- Copiando estrutura para tabela agendamento.servico
CREATE TABLE IF NOT EXISTS `servico` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(30) DEFAULT NULL,
  `preco` double NOT NULL,
  `tipoServico` varchar(30) NOT NULL,
  `codUsuarioInclusao_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_diduwvxi114f9gmyu661dhh07` (`codUsuarioInclusao_codigo`),
  CONSTRAINT `FK_diduwvxi114f9gmyu661dhh07` FOREIGN KEY (`codUsuarioInclusao_codigo`) REFERENCES `usuario` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela agendamento.servico: ~7 rows (aproximadamente)
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` (`codigo`, `descricao`, `preco`, `tipoServico`, `codUsuarioInclusao_codigo`) VALUES
	(1, 'banho tradicional', 50, 'banho', 1),
	(3, 'teste', 50, 'teste', 1),
	(5, 'teste', 50, 'teste', 1),
	(6, 'teste', 50, 'teste', 1),
	(7, 'ssss', 30, 'bts', 1),
	(8, 'hfdshaklfasdf', 30, 'qwert', 1),
	(9, 'teste', 10, 'teste', 1),
	(10, 'serviço de teste', 10, 'teste', 1);
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;

-- Copiando estrutura para tabela agendamento.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(30) DEFAULT NULL,
  `cep` varchar(9) DEFAULT NULL,
  `cidade` varchar(30) DEFAULT NULL,
  `cpf` varchar(14) NOT NULL,
  `dataNascimento` date DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `funcao` varchar(45) NOT NULL,
  `nivelUsuario` varchar(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `numero` int(11) DEFAULT NULL,
  `rg` varchar(9) DEFAULT NULL,
  `rua` varchar(50) DEFAULT NULL,
  `senha` varchar(20) NOT NULL,
  `telefone` varchar(13) DEFAULT NULL,
  `usuario` varchar(20) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela agendamento.usuario: ~13 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`codigo`, `bairro`, `cep`, `cidade`, `cpf`, `dataNascimento`, `email`, `estado`, `funcao`, `nivelUsuario`, `nome`, `numero`, `rg`, `rua`, `senha`, `telefone`, `usuario`) VALUES
	(1, 'centro', '18860-000', 'Timburi', '12312312312', NULL, 'lucas@gmail.com', 'Paraná', 'Estagiario', 'Administrador', 'Lucas Oliveira da Cru', 15, '12312312', 'Rua Rio Teste', 'qwerty', '123123123', 'locrz'),
	(2, 'dos dinos', '0000000', 'rio branco', '4817960584', NULL, 'jooj@gmail.com', '', 'Function', 'Administrador', 'Joao Tomaz', 35, '12314122', 'Rua dos Projetos', 'teste', '99867544', 'jooh'),
	(4, '', '', '', '4817950633', NULL, '', '', 'atendente', 'Administrador', 'Lucas', NULL, '', '', 'qwerty', '', 'lucas'),
	(5, '', '', '', '12312312312', NULL, '', '', 'teste', 'Administrador', 'teste', NULL, '', '', 'teste', '', 'teste'),
	(6, '', '', '', '12312312312', NULL, '', '', 'teste', 'Administrador', 'teste', NULL, '', '', 'teste', '', 'teste'),
	(7, '', '', '', '17236123812', NULL, '', '', 'teste', 'Administrador', 'Gustavo', NULL, '', '', 'qwerty', '', 'gustavo'),
	(8, '', '', '', '12311312312', NULL, '', '', 'teste', 'Administrador', 'teste', NULL, '', '', 'teste', '', 'teste'),
	(9, '', '', '', '43646354354', NULL, '', '', 'eeee', 'Administrador', 'teste1', NULL, '', '', 'teste', '', 'luas'),
	(10, '', '', '', '12344432343', NULL, '', '', 'teste', 'Administrador', 'Teste1', NULL, '', '', 'tretre', '', 'qwe'),
	(11, '', '', '', '12312312321', NULL, '', '', 'teste', 'Administrador', 'teste', NULL, '', '', 'teste', '', 'teste'),
	(12, '', '18860-000', 'Timburi', '4817960514', NULL, '', '', 'qwe', 'Administrador', 'Lucas Oliveira da Cruz', NULL, '', '', 'teste', '', 'test'),
	(13, '', '', '', '87436787378', NULL, '', '', 'teste', 'Padrão', 'Lucas', NULL, '', '', 'trewtrew', '', 'tesssa'),
	(14, '', '', '', '11311312312', NULL, '', '', 'Estudante', 'Padrão', 'Leticia aparecida', NULL, '', '', 'qweqw', '', 'lele'),
	(15, 'teste', '18860-000', 'TIMBURI', '48179127371', '2019-04-24', 'renan123@hotmail.com', 'Paraná', 'teste', 'Padrão', 'Renan', 12, '585111765', 'teste', 'daocu', '148822221', 'renanzinho');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
