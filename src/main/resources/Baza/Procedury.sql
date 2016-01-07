use konfigurator;
GO


IF (OBJECT_ID('GetAll_Products') IS NOT NULL)
  DROP FUNCTION GetAll_Products
GO
CREATE FUNCTION GetAll_Products ()
RETURNS @tab TABLE (id INT,
	name VARCHAR(30),
	standardPrice MONEY)
AS
BEGIN
	INSERT INTO @tab 
	SELECT id, name, standardPrice
	FROM products
	WHERE isActive = 1
	RETURN
END
GO


IF (OBJECT_ID('GetAll_Options') IS NOT NULL)
  DROP FUNCTION GetAll_Options
GO
CREATE FUNCTION GetAll_Options ()
RETURNS @tab TABLE (id INT,
	name VARCHAR(30),
	isDefault BIT,
	price MONEY,
	products INT,
 	groups INT)
AS
BEGIN
	INSERT INTO @tab 
	SELECT O1.id, O1.name, O1.isDefault, O1.price, (SELECT id FROM
													products WHERE
													id IN
													(SELECT productID FROM productToOptionLink
													WHERE optionID = O1.id)) AS 'p',
													(SELECT id FROM
													groups WHERE
													id IN
													(SELECT groupID FROM productToOptionLink
													WHERE optionID = O1.id)) AS 'g'
	FROM options O1
	WHERE isActive = 1
	ORDER BY g ASC
	RETURN
END
GO

IF (OBJECT_ID('GetAll_Rules') IS NOT NULL)
  DROP FUNCTION GetAll_Rules
GO
CREATE FUNCTION GetAll_Rules ()
RETURNS @tab TABLE (id INT,
	productID INT,
	option1ID INT,
	option2ID INT)
AS
BEGIN
	INSERT INTO @tab 
	SELECT *
	FROM rules
	RETURN
END
GO


IF (OBJECT_ID('GetAll_Groups') IS NOT NULL)
  DROP FUNCTION GetAll_Groups
GO
CREATE FUNCTION GetAll_Groups ()
RETURNS @tab TABLE (id INT,
	groupName VARCHAR(30))
AS
BEGIN
	INSERT INTO @tab 
	SELECT *
	FROM groups
	RETURN
END
GO


IF (OBJECT_ID('GetAll_ProductOptions') IS NOT NULL)
  DROP FUNCTION GetAll_ProductOptions
GO
CREATE FUNCTION GetAll_ProductOptions (@productID INT)
RETURNS @tab TABLE (id INT,
	name VARCHAR(30),
	isDefault BIT,
	price MONEY,
	groups INT)
AS
BEGIN
	INSERT INTO @tab
	SELECT O1.id, O1.name, O1.isDefault, O1.price,	(SELECT id FROM
													groups WHERE
													id IN
													(SELECT groupID FROM productToOptionLink
													WHERE optionID = O1.id)) AS 'g'
	FROM
	options O1
	WHERE
	O1.id IN
		(SELECT PO1.optionID FROM
		productToOptionLink PO1
		WHERE
		PO1.productID = @productID)
	ORDER by g ASC	
	RETURN	
END
GO

IF (OBJECT_ID('Create_Products') IS NOT NULL)
  DROP PROCEDURE Create_Products
GO
CREATE PROCEDURE Create_Products @name VARCHAR(40), @standardPrice MONEY
AS
BEGIN
	INSERT INTO products (name, standardPrice) VALUES (@name, @standardPrice)
END
GO


IF (OBJECT_ID('Create_Groups') IS NOT NULL)
  DROP PROCEDURE Create_Groups
GO
CREATE PROCEDURE Create_Groups @name VARCHAR(40)
AS
BEGIN
	INSERT INTO groups VALUES (@name)
END
GO


IF (OBJECT_ID('groupName2ID') IS NOT NULL)
  DROP FUNCTION groupName2ID
GO
CREATE FUNCTION groupName2ID (@name VARCHAR (30))
RETURNS INT
BEGIN
	DECLARE @id INT;
	SELECT @id = id FROM groups 
	WHERE groupName = @name;
	
	RETURN @id
END
GO


IF (OBJECT_ID('productName2ID') IS NOT NULL)
  DROP FUNCTION productName2ID
GO
CREATE FUNCTION productName2ID (@name VARCHAR (30))
RETURNS INT
BEGIN
	DECLARE @id INT;
	SELECT @id = id FROM products 
	WHERE name = @name;
	
	RETURN @id
END
GO


IF (OBJECT_ID('Create_Options') IS NOT NULL)
  DROP PROCEDURE Create_Options
GO
CREATE PROCEDURE Create_Options @productID INT, @optionName VARCHAR(30), @price MONEY, @default BIT, @groupID INT
AS
BEGIN
	IF (@default = 1)
	BEGIN
		UPDATE options SET isDefault = 0
		WHERE id IN
			(SELECT optionID FROM
			productToOptionLink 
			WHERE
			productID = @productID
			AND
			groupID = @groupID
			)
	END
	INSERT INTO options (name, isDefault, price) VALUES ( @optionName, @default, @price)
	INSERT INTO productToOptionLink (productID, optionID, groupID) VALUES (@productID, @@IDENTITY, @groupID)
END
GO

/*
IF (OBJECT_ID('ChangeOptionGroup') IS NOT NULL)
  DROP PROCEDURE ChangeOptionGroup
GO
CREATE PROCEDURE ChangeOptionGroup @optionID INT, @newGroup INT
AS
BEGIN
	UPDATE productToOptionLink
	SET groupID = @newGroup
	WHERE optionID = @optionID
END
GO
*/


IF (OBJECT_ID('DelOpt') IS NOT NULL)
  DROP TRIGGER DelOpt
GO
CREATE TRIGGER DelOpt
ON options
INSTEAD OF DELETE
AS
BEGIN
	DELETE FROM rules
	WHERE
	option1ID IN
	(SELECT id FROM deleted)
	OR
	option2ID IN
	(SELECT id FROM deleted)
	
	DELETE FROM productToOptionLink
	WHERE optionID IN
	(SELECT id FROM DELETED)
	
	UPDATE options SET isActive = 0
	WHERE id IN (SELECT id FROM deleted)
	
 	UPDATE options SET name = 'del_' + name 
 	WHERE id IN (SELECT id FROM deleted)
 	
END
GO


IF (OBJECT_ID('Remove_Options') IS NOT NULL)
  DROP PROCEDURE Remove_Options
GO
CREATE PROCEDURE Remove_Options @optionID INT, @newDefaultOption INT = NULL
AS
BEGIN
	DECLARE  @default BIT
	SELECT @default = isDefault FROM options where id= @optionID
	
	IF (@default = 1 AND @newDefaultOption IS NULL) 
	BEGIN
		RETURN
	END

	IF (@optionID = @newDefaultOption) 
	BEGIN
		RETURN
	END
	
	
	IF (@default = 1)
	BEGIN
		UPDATE options SET isDefault = 1
		WHERE id = @newDefaultOption
 	END
 	BEGIN
 		DELETE FROM options 
 		WHERE id = @optionID
 	END
 	IF (@default = 0)
 	BEGIN
 		DELETE FROM options
 		WHERE id = @optionID
 	END
END
GO


IF (OBJECT_ID('Remove_Products') IS NOT NULL)
  DROP PROCEDURE Remove_Products
GO
CREATE PROCEDURE Remove_Products @productID INT
AS
BEGIN
	BEGIN
		DELETE FROM options
		WHERE id IN
			(SELECT optionID FROM productToOptionLink
			WHERE productID = @productID
			AND
			optionID = id)
	END
	BEGIN
		UPDATE products SET isActive = 0
		WHERE id = @productID
		
		UPDATE products SET name = 'del_' + name + '_'+CAST(id AS VARCHAR)
		WHERE id = @productID
	END
END
GO

IF (OBJECT_ID('Create_Rules') IS NOT NULL)
  DROP PROCEDURE Create_Rules
GO
CREATE PROCEDURE Create_Rules @productID INT, @option1ID INT, @option2ID INT
AS
BEGIN
	INSERT INTO rules VALUES (@productID, @option1ID, @option2ID)
END
GO



/*
IF (OBJECT_ID('ShowRulesForOption') IS NOT NULL)
DROP PROCEDURE ShowRulesForOption
GO
CREATE PROCEDURE ShowRulesForOption @optionID INT
AS
BEGIN
	SELECT id AS 'nr reguły', option1ID AS 'opcja 1', option2ID AS 'opcja 2'
	FROM rules
	WHERE option1ID = @optionID
	OR
	option2ID = @optionID
END
GO
*/



IF (OBJECT_ID('Get_AllowedOptions') IS NOT NULL)
  DROP FUNCTION Get_AllowedOptions
GO
CREATE FUNCTION Get_AllowedOptions (@productID INT, @optionID INT)
RETURNS @tab TABLE (id INT,
	name VARCHAR(30),
	isDefault BIT,
	price MONEY,
	groups INT)
AS
BEGIN
	INSERT INTO @tab
	SELECT O1.id, O1.name, O1.isDefault, O1.price,	(SELECT id FROM
													groups WHERE
													id IN
													(SELECT groupID FROM productToOptionLink
													WHERE optionID = O1.id)) AS 'g'
	FROM options O1
	WHERE 
	O1.id IN
	(SELECT optionID FROM productToOptionLink P1
	WHERE productID  = @productID)
	AND
	O1.id <> @optionID
	AND 
	O1.id NOT IN
		(SELECT option1ID FROM
		rules
		WHERE option2ID = @optionID)
	AND 
	O1.id NOT IN
		(SELECT option2ID FROM
		rules
		WHERE option1ID = @optionID)
	AND		
	O1.isActive = 1	
	RETURN	
END
GO



IF (OBJECT_ID('GetByID_Products') IS NOT NULL)
  DROP FUNCTION GetByID_Products
GO
CREATE FUNCTION GetByID_Products(@productID INT)
RETURNS @tab TABLE (id INT,
	name VARCHAR(30),
	standardPrice MONEY)
AS
BEGIN
	INSERT INTO @tab 
	SELECT id, name, standardPrice
	FROM products
	WHERE isActive = 1
	AND id = @productID
	RETURN
END
GO


IF (OBJECT_ID('GetByID_Options') IS NOT NULL)
  DROP FUNCTION GetByID_Options
GO
CREATE FUNCTION GetByID_Options (@optionID INT)
RETURNS @tab TABLE (id INT,
	name VARCHAR(30),
	isDefault BIT,
	price MONEY,
	products INT,
 	groups INT)
AS
BEGIN
	INSERT INTO @tab 
	SELECT O1.id, O1.name, O1.isDefault, O1.price, (SELECT id FROM
													products WHERE
													id IN
													(SELECT productID FROM productToOptionLink
													WHERE optionID = O1.id)) AS 'p',
													(SELECT id FROM
													groups WHERE
													id IN
													(SELECT groupID FROM productToOptionLink
													WHERE optionID = O1.id)) AS 'g'
	FROM options O1
	WHERE isActive = 1
	AND
	id = @optionID
	RETURN
END
GO

IF (OBJECT_ID('GetByID_Rules') IS NOT NULL)
  DROP FUNCTION GetByID_Rules
GO
CREATE FUNCTION GetByID_Rules(@ruleID INT)
RETURNS @tab TABLE (id INT,
	productID INT,
	option1ID INT,
	option2ID INT)
AS
BEGIN
	INSERT INTO @tab 
	SELECT *
	FROM rules
	WHERE
	id = @ruleID
	RETURN
END
GO


IF (OBJECT_ID('GetByID_Groups') IS NOT NULL)
  DROP FUNCTION GetByID_Groups
GO
CREATE FUNCTION GetByID_Groups(@groupID INT)
RETURNS @tab TABLE (id INT,
	groupName VARCHAR(30))
AS
BEGIN
	INSERT INTO @tab 
	SELECT *
	FROM groups
	WHERE
	id = @groupID
	RETURN
END
GO


IF (OBJECT_ID('Update_Products') IS NOT NULL)
  DROP PROCEDURE Update_Products
GO
CREATE PROCEDURE Update_Products @productID INT, @name VARCHAR(30), @standardPrice MONEY
AS
BEGIN
	UPDATE products SET name = @name
	WHERE id = @productID
	
	UPDATE products SET standardPrice = @standardPrice
	WHERE id = @productID
END
GO


IF (OBJECT_ID('Update_Options') IS NOT NULL)
  DROP PROCEDURE Update_Options
GO
CREATE PROCEDURE Update_Options @optionID INT, @name VARCHAR(30), @price MONEY
AS
BEGIN
	UPDATE options SET name = @name
	WHERE id = @optionID
	
	UPDATE options SET price = @price
	WHERE id = @optionID
END
GO

IF (OBJECT_ID('Update_Rules') IS NOT NULL)
  DROP PROCEDURE Update_Rules
GO
CREATE PROCEDURE Update_Rules @ruleID INT, @name VARCHAR(30)
AS
BEGIN
	UPDATE products SET name = @name
	WHERE id = @ruleID
END
GO


IF (OBJECT_ID('Update_Groups') IS NOT NULL)
  DROP PROCEDURE Update_Groups
GO
CREATE PROCEDURE Update_Groups @groupID INT, @name VARCHAR(30)
AS
BEGIN
	UPDATE products SET name = @name
	WHERE id = @groupID
END
GO


IF (OBJECT_ID('Get_NotAllowedOptions') IS NOT NULL)
  DROP FUNCTION Get_NotAllowedOptions
GO
CREATE FUNCTION Get_NotAllowedOptions (@productID INT, @optionID INT)
RETURNS @tab TABLE (id INT,
	name VARCHAR(30),
	isDefault BIT,
	price MONEY,
	groups INT)
AS
BEGIN
	INSERT INTO @tab
	SELECT O1.id, O1.name, O1.isDefault, O1.price,	(SELECT id FROM
									groups WHERE
                        					id IN
									(SELECT groupID FROM productToOptionLink
									WHERE optionID = O1.id)) AS 'g'
	FROM options O1
	WHERE 
	O1.id IN
	(SELECT optionID FROM productToOptionLink P1
	WHERE productID  = @productID)
	AND
	O1.id <> @optionID
	AND 
	O1.id IN
		(SELECT option1ID FROM
		rules
		WHERE option2ID = @optionID)
	OR
	O1.id IN
		(SELECT option2ID FROM
		rules
		WHERE option1ID = @optionID)
	AND		
	O1.isActive = 1	
	RETURN	
END
GO