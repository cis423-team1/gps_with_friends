CREATE TABLE Track_History
(
    UID             INT,
    Data            DATE,
    Time            TIME,
    Location_x      FLOAT(32,30),
    Location_y      FLOAT(32,30),
    UserList_UID    INT,
    FOREIGN KEY (UserList_UID) REFERENCES User(UID)
);

CREATE TABLE User
(
    UID             INT             NOT NULL,
    Fname           VARCHAR(45),
    Lname           VARCHAR(45),
    Email           VARCHAR(45),
    PRIMARY KEY (UID)
);

CREATE TABLE Location
(
    UID             INT             NOT NULL,
    Date            DATE,
    Time            TIME,
    Location_x      FLOAT(32,30),
    Location_y      FLOAT(32,30),
    UserList_UID    INT,
    PRIMARY KEY (UID),
    FOREIGN KEY (UserList_UID) REFERENCES User(UID)
);

CREATE TABLE Group
(
    Group_ID        INT             NOT NULL,
    Group_Name      VARCHAR(45),
    Owner_ID        INT,
    UserList_UID    INT,
    PRIMARY KEY (Group_ID),
    FOREIGN KEY (UserList_UID) REFERENCES User(UID)
);

CREATE TABLE Group_Lists
(
    Group_ID        INT,
    UID             INT,
    UserList_UID    INT,
    FOREIGN KEY (Group_GroupID) REFERENCES Group(Group_ID)
);

CREATE TABLE Authentication
(
    UID_Tracker     INT,
    UID_Trackee     INT,
    Data            DATE,       
    Time            TIME,
    UserList_UID    INT,
    FOREIGN KEY (User_UID) REFERENCES User(UID)
);
