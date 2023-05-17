package com.altuera.ignite;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.SplittableRandom;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonGenerator {
    private static final String[] firstNames = {"Treasure", "June", "Brice", "Oswaldo", "Rasheed", "Kirstyn", "Gaven", "Abagail", "Marian", "Gladys", "Jay",
            "Robyn", "Kyree", "Orion", "Cecil", "Gunnar", "Philip", "Stacy", "Todd", "Tayla", "Joshua", "Meredith", "Kayli", "Darren", "Caleigh", "Mitchell",
            "Raul", "Sonja", "Alannah", "Julianne", "Silas", "Jasmin", "Domenic", "Amber", "Terence", "Lester", "Delanie", "Elise", "Brendan", "Dalia", "Tyra",
            "Triniti", "Xzavier", "Keyonna", "Hailey", "Tevin", "Cierra", "Kai", "Ezekiel", "Rolando", "Emerald", "Camron", "Gabriela", "Sarai", "Elyse",
            "Mitchel", "Jaci", "Shayne", "Anabelle", "August", "Kale", "Rayshawn", "Brendon", "Lyric", "Lesly", "Dylan", "Dusty", "Delia", "Carlos",
            "Alejandro", "Lee", "Melody", "Callista", "Amiah", "Carly", "Tyasia", "Byron", "Ella", "Chelsie", "Lizeth", "Jacinda", "Abbie", "Oscar",
            "Alberto", "Randolph", "Misty", "Alessandro", "Yosef", "Halle", "Glenn", "Benjamin", "Larry", "Emma", "Malika", "Coral", "Devon", "Garrison",
            "Donavan", "Salma", "Liam", "Trenten", "Harley", "Tammy", "Paulina", "Emerson", "Kathrine", "Leah", "Darrell", "Gissel", "Khalil", "Kayla",
            "Reece", "Berenice", "Emilia", "Demonte", "Jeremiah", "Clyde", "Alan", "Tony", "Ciarra", "Brianna", "Jordan", "Carlton", "Cale", "Gordon", "Oliver",
            "Iris", "Isabela", "Vanessa", "Austen", "Theo", "Ernesto", "Kaycee", "Sawyer", "Justyn", "Griffin", "Brittany", "Tai", "Jerome", "Keila", "Alexia",
            "Charisma", "Celia", "James", "Edith", "Desean", "Jacquelin", "Tara", "Alison", "Audra", "Chauncey", "Lesley", "Dakotah", "Moises", "Kacie",
            "Genesis", "Infant", "Ruth", "Neil", "Renee", "Lisbeth", "Madison", "Heidy", "Tatiana", "Tristin", "Samara", "Israel", "Rickey", "Stevie",
            "Mallorie", "Neal", "Piper", "Madisyn", "Cyrus", "Cole", "Dominique", "Franklin", "Corinne", "Miracle", "Elian", "Giovanni", "Ivana", "Ray",
            "Antony", "Ryder", "Gene", "Sierra", "Dean", "Osvaldo", "Kinsley", "Nyasia", "Luke", "Korbin", "Jayvon", "Milan", "Jovani", "Jacklyn", "Delilah",
            "Raymundo", "Araceli"};

    private static final String[] lastNames = {"Lloyd", "DeJesus", "Clouse", "Herrick", "Mosley", "Hollis", "Dexter", "Ingle", "Mehta", "Layton", "Condon",
            "Best", "Packer", "Templeton", "Butler", "Pyle", "France", "Colson", "Fraley", "Kuhn", "Nielsen", "Marino", "Burks", "Shanahan", "Bunting",
            "Crooks", "Foote", "Evans", "Lundy", "Lauer", "Shank", "Moses", "Stoll", "Burgos", "Michaels", "Alley", "Olivares", "Sheridan", "Noe", "Dozier",
            "Willson", "Flint", "Cone", "Seward", "Slack", "Lovelace", "McKnight", "Olivas", "Wooten", "Wilder", "Abreu", "Beauchamp", "Lamar", "Sizemore",
            "Shuman", "Tovar", "Fox", "Lawrence", "Cummins", "Ulloa", "Troyer", "Overton", "Smalls", "Hoy", "Montes", "Wetzel", "Simpson", "Bryant", "Shields",
            "Davison", "Dagostino", "Newby", "Beckman", "Bronson", "Ewing", "Murrell", "Coon", "Preston", "Rea", "Smallwood", "Gulley", "Earl", "Forman",
            "Judd", "Hope", "Cross", "Lira", "Bingham", "Grossman", "Nadeau", "Adkins", "Doe", "Caraballo", "Everhart", "Rau", "Cagle", "Flood", "Tan", "Xiong",
            "Garner", "Altamirano", "Giron", "Holcomb", "Hadley", "Chapin", "Delacruz", "Rowley", "Hinds", "Griggs", "Purcell", "Warren", "Villasenor",
            "Reiter", "Shelby", "Harwood", "Drummond", "Fletcher", "Glover", "Hyatt", "Fernandez", "Neill", "Hoffmann", "Coburn", "Penn", "Spring", "Massey",
            "Childs", "Brandt", "Kruger", "Bueno", "Weathers", "Reddick", "Duff", "Giles", "Christy", "Robertson", "Hood", "Desantis", "Ordonez", "Gallagher",
            "Howell", "Dwyer", "Sturgill", "Singh", "Augustine", "Mateo", "Wolfe", "Perea", "StClair", "Bull", "Robison", "McFadden", "Jankowski", "Sawyer",
            "Alfonso", "Morales", "Ragland", "Wisniewski", "Byler", "Bailey", "Ellsworth", "Goodson", "Vo", "Hartmann", "Ames", "Whitmore", "Withers",
            "Bourgeois", "Vail", "Rudd", "Montano", "Denney", "Bertrand", "Cutler", "Rubin", "Vaughn", "Varney", "Ingram", "Fanning", "Merrill", "Suarez",
            "Loomis", "Coles", "Lorenzo", "Frye", "Seay", "Gleason", "Tidwell", "Sandoval", "Norman", "Higginbotham", "Bourne", "Friend", "Concepcion", "Wren",
            "Stanfield", "Owens", "Reed", "McLaughlin", "Brennan"};

    private static final LocalDate START_BIRTHDATE = LocalDate.of(1950, 1,1);
    private static final LocalDate END_BIRTHDATE = LocalDate.of(2002, 1,1);

    private static final long RANGE = ChronoUnit.DAYS.between(START_BIRTHDATE, END_BIRTHDATE);

    private static final PersonGenerator instance = new PersonGenerator();

    private final SplittableRandom rand = new SplittableRandom(42);//fixed seed for predictable results during demos.


    public static PersonGenerator getInstance() {
        return instance;
    }

    public Person generateRandomPerson() {
        return generateRandomPerson(UUID.randomUUID());
    }

    public Person generateRandomPerson(UUID id) {
        return Person.builder()
                     .id(id)
                     .firstName(firstNames[rand.nextInt(firstNames.length)])
                     .lastName(lastNames[rand.nextInt(lastNames.length)])
                     .birthday(randomBirthday())
                     .ssn(randomSsn())
                     .build();
    }

    private LocalDate randomBirthday() {
        return START_BIRTHDATE.plusDays(rand.nextLong(RANGE));
    }

    private String randomSsn() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            String num = String.valueOf(rand.nextInt(1000));

            for (int j = num.length(); j < 3; ++j) {
                sb.append('0');
            }

            sb.append(num);

            sb.append('-');
        }

        sb.delete(sb.length()-1, sb.length());


        return sb.toString();
    }
}
