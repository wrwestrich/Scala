//Censor trait demo

trait Censor{
    val censorDefs = io.Source.fromFile("./censor.txt");
    var censorMap = Map[String, String]();

    censorMap = censorDefs.getLines.foldLeft(censorMap){
        (map, data) =>
        val terms = data.split(",");
        (map + (terms(0) -> terms(1)))
    }

    def censor(text: String) = {
        censorMap.foldLeft(text)(
            (censoredText, mapping) => censoredText.replaceAll(("(?i)\\b" + mapping._1 + "\\b"), mapping._2)
        )
    }
}

class Paragraph(val text: String) extends Censor{
    def getText() = this.text;

    def getCensoredText() = this.censor(this.text);
}

val text = new Paragraph(
    "Shoot likes darn."
);

println("Original Text:");
println(text.getText());

println("\nCensored Text:");
println(text.getCensoredText());
