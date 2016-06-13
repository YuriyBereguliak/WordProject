USE [Word Project]
GO

/****** Object:  StoredProcedure [dbo].[ProcedureVerifyEnglishWord]    Script Date: 24.04.2016 22:40:04 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Yuriy Bereguliak>
-- =============================================
CREATE PROCEDURE [dbo].[ProcedureVerifyEnglishWord]
	@wordUA NVARCHAR(30),
	@wordEN NVARCHAR(30),
	@vocabulary NVARCHAR(30)
AS
DECLARE @wordID INT
DECLARE @wordDB NVARCHAR(30)
DECLARE @count INT
DECLARE @i INT
DECLARE @equals INT
BEGIN
	SET @count = 0;
	SET @count = (SELECT COUNT(WordID) FROM Words WHERE UkrainianWord = @wordUA);
	IF(@count > 1 )
		BEGIN
			CREATE TABLE #EnglishValues (word NVARCHAR(30));
			INSERT INTO #EnglishValues(word) SELECT EnglishWord FROM Words WHERE UkrainianWord = @wordUA;
			SET @equals = (SELECT COUNT(word) FROM #EnglishValues WHERE word = @wordEN);
			IF(@equals >= 1)
				BEGIN
					SET @count = (SELECT COUNT(WordID) FROM Word_Vocabulary
						WHERE WordID = (SELECT WordID FROM Words WHERE EnglishWord = @wordEn)
						AND VocabularyID = (SELECT VocabularyID FROM Vocabularies WHERE VocabularyName = @vocabulary));
					IF(@count = 1)
						RETURN 1
					ELSE
						RETURN 0
				END	
		END
	ELSE
		BEGIN
			IF(@count = 1 )
				BEGIN
					SET @wordID = (SELECT WordID FROM Words WHERE UkrainianWord = @wordUA);
					SET @wordDB = (SELECT EnglishWord FROM Words WHERE WordID = @wordID);
					IF(@wordEN = @wordDB)
						BEGIN
							SET @count = (SELECT COUNT(WordID) FROM Word_Vocabulary
								WHERE WordID = (SELECT WordID FROM Words WHERE EnglishWord = @wordEn)
								AND VocabularyID = (SELECT VocabularyID FROM Vocabularies WHERE VocabularyName = @vocabulary));
							IF(@count = 1)
								RETURN 1
							ELSE
								RETURN 0
						END
					ELSE
						RETURN 0
				END
			ELSE
				RETURN 1
		END
END

GO


