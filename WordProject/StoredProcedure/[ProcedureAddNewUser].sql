USE [Word Project]
GO
/****** Object:  StoredProcedure [dbo].[ProcedureAddNewUser]    Script Date: 24.04.2016 22:37:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author: Yuriy Bereguliak>
-- =============================================
-- Додання нового користувача	
ALTER PROCEDURE [dbo].[ProcedureAddNewUser] 
	@UserName nvarchar(50)
AS
DECLARE @count int
BEGIN
	SET @count = (SELECT COUNT(Users.UserName) from Users where UserName = @UserName);
	IF(@count = 0)
		BEGIN
			INSERT INTO Users(UserName) VALUES (@UserName);
			RETURN 1;
		END
	ELSE
		RETURN 0;		
END
