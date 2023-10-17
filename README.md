# EUTech-Assignment-3
Release v1.01

## How to use:
On first launch, a prompt will appear to enter a username and a password for an account that *MUST* have administrator status. Otherwise it will not be possible to access the administrator tools.

After entering the data mentioned above, press the enter button and your account will be saved and the program will close. Once this happens, the program must be launched again and normal functionality will ensue.

You must login with the account that was just created as it is the only available user as of this moment. Since this user has administrator status, you may access all tools. To create a new user, open the side panel using the button located at the top left.

> **IMPORTANT NOTICE: If for any reason you forget the credentials to your account (or have lost access to all accounts with administrator status), the only way to regain admin access is by deleting the database located in the Documents folder, which is a folder named UIDSecrets.**

> **Please note that in doing this, all data for the program will be removed, including all inventory entries and stored users, therefore it is essential that the credentials to at least one administrator account are stored in a secure place.**

## Administrator Tools:
Aside from the user creation tool, accounts with the administrator status will have access to the following tools:
1. Add Item Tool:
   This tool allows the user to create an item to add to the inventory of the database. 
   This item should look like the following example:
   
   > Name: Simple USB Keyboard | Amount: 4 | Description: A simple plug and play keyboard made of black plastic | Price: 15

2. Edit Item Tool:
   This tool allows the user to edit an item based on the ID it has, which can be viewed in the View Items Tool from the Regular User section.
   Fields that are left empty will remain unchanged.
3. Delete item tool:
   This tool allows the user to delete an item from the database based on the ID it has. 
   
## Regular User Tools:
Regular users only have access to the following tools:
1. View Items Tool:
   Allows the user to view a list of all items in the inventory of the database, ordered by ID.
2. Search Items Tool:
   Allows the user to search the inventory for a specific item based on the name or the ID it has.
3. Export Items Tool:
   Allows the user to export the entire inventory to a PDF file named MostRecentReport.pdf, which is stored in the Documents folder.
   
### Code Details
Written 100% in Java. User Interface built using Java Swing. Database created using Apache Derby.
