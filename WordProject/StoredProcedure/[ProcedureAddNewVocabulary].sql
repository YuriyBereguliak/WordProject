USE [Word Project]
GO

/****** Object:  StoredProcedure [dbo].[ProcedureAddNewVocabulary]    Script Date: 24.04.2016 22:37:45 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author, Yuriy Bereguliak>
-- =============================================
CREATE PROCEDURE [dbo].[ProcedureAddNewVocabulary] 
	@VocabularyName NVARCHAR(30)
AS
DECLARE @count INT
BEGIN
	--отримання кількості словників
	SET @count = (SELECT COUNT(Vocabularies.VocabularyName) from Vocabularies where VocabularyName = @VocabularyName);
	--якщо така кількість рівна 0, тоді зробити вставку до таблиці словників
	--та повернути 1, в іншому випадку повернути 0
	IF(@count = 0)
		BEGIN
			INSERT INTO Vocabularies(VocabularyName) VALUES (@VocabularyName);
			RETURN 1;
		END
	ELSE
		RETURN 0;	
END

GO


