create schema if not exists game_store;
use game_store;

create table if not exists game (
    game_id int(11) not null auto_increment primary key,
    title varchar(50) not null,
    esrb_rating varchar(50) not null,
    description varchar(255) not null,
    price decimal(5, 2) not null,
    studio varchar(50) not null,
    quantity int(11)
);

create table if not exists console (
    console_id int(11) not null auto_increment primary key,
    model varchar(50) not null,
    manufacturer varchar(50) not null,
    memory_amount varchar(20),
    processor varchar(20),
    price decimal(5, 2) not null,
    quantity int(11) not null
);

create table if not exists t_shirt (
    t_shirt_id int(11) not null auto_increment primary key,
    size varchar(20) not null,
    color varchar(20) not null,
    description varchar(255) not null,
    price decimal(5,2) not null,
    quantity int(11) not null
);

create table if not exists sales_tax_rate (
    state char(2) not null,
    rate decimal(3,2) not null
);

create unique index ix_state_rate on sales_tax_rate (state, rate);

create table if not exists processing_fee (
    product_type varchar(20) not null,
    fee decimal (4,2)
);

create unique index ix_product_type_fee on processing_fee (product_type, fee);

create table if not exists invoice (
    invoice_id int(11) not null auto_increment primary key,
    name varchar(80) not null,
    street varchar(30) not null,
    city varchar(30) not null,
    state varchar(30) not null,
    zipcode varchar(5) not null,
    item_type varchar(20) not null,
    item_id int(11) not null,
    unit_price decimal(5,2) not null,
    quantity int(11) not null,
    subtotal decimal(5,2) not null,
    tax decimal(5,2) not null,
    processing_fee decimal (5,2) not null,
    total decimal(5,2) not null
);


Alabama - AL: .05
Alaska - AK: .06
Arizona - AZ: .04
Arkansas - AR: .06
California - CA: .06
Colorado - CO: .04
Connecticut - CT: .03
Delaware - DE: .05
Florida - FL: .06
Georgia - GA: .07
Hawaii - HI: .05
Idaho - ID: .03
Illinois - IL: .05
Indiana - IN: .05
Iowa - IA: .04
Kansas - KS: .06
Kentucky - KY: .04
Louisiana - LA: .05
Maine - ME: .03
Maryland - MD: .07
Massachusetts - MA: .05
Michigan - MI: .06
Minnesota - MN: .06
Mississippi - MS: .05
Missouri - MO: .05
Montana - MT: .03
Nebraska - NE: .04
Nevada - NV: .04
New Hampshire - NH: .06
New Jersey - NJ: .05
New Mexico - NM: .05
New York - NY: .06
North Carolina - NC: .05
North Dakota - ND: .05
Ohio - OH: .04
Oklahoma - OK: .04
Oregon - OR: .07
Pennsylvania - PA: .06
Rhode Island - RI: .06
South Carolina - SC: .06
South Dakota - SD: .06
Tennessee - TN: .05
Texas - TX: .03
Utah - UT: .04
Vermont - VT: .07
Virginia - VA: .06
Washington - WA: .05
West Virginia - WV: .05
Wisconsin - WI: .03
Wyoming - WY: .04


Processing Fees
Consoles: 14.99
T-Shirts: 1.98
Games: 1.49
