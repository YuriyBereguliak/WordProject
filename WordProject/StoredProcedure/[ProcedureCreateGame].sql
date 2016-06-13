USE [Word Project]
GO

/****** Object:  StoredProcedure [dbo].[ProcedureCreateGame]    Script Date: 24.04.2016 22:38:31 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Yuriy Bereguliak>
-- =============================================
CREATE PROCEDURE [dbo].[ProcedureCreateGame]
	@vocabulary NVARCHAR(30)
AS
DECLARE @vocabularyID INT
DECLARE @gameID INT
BEGIN
	SET @vocabularyID = (SELECT VocabularyID FROM Vocabularies WHERE VocabularyName = @vocabulary);
	INSERT INTO Games(VocabularyID) VALUES (@vocabularyID);
	SET @gameID = (SELECT SCOPE_IDENTITY());
	RETURN @gameID;
END

GO


