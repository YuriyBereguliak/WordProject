USE [Word Project]
GO

/****** Object:  StoredProcedure [dbo].[ProcedureDeleteAbortGameData]    Script Date: 24.04.2016 22:38:47 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Yuriy Bereguliak>
-- =============================================
CREATE PROCEDURE [dbo].[ProcedureDeleteAbortGameData]
	@gameID INT
AS
BEGIN
	DELETE FROM Moves WHERE GameID = @gameID;
	DELETE FROM GAMES WHERE GameID = @gameID;
END

GO


