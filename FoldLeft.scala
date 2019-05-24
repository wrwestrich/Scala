//Foldleft demonstration

val names = List(
    "Bob",
    "John",
    "Seth",
    "Will",
    "Kosa",
    "Philip"
);

def charCount = (0 /: names){
    (sum, names) => (sum + names.size)
}

println("\n" + names);
println("Total characters: " + charCount);
