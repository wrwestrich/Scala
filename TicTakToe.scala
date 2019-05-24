//TicTakToe game in Scala

//Create a class to define the behavior of a cell on the board
class Cell{
    //Either X or O placed in this cell
    var marking = "";

    //Define what is allowed in each cell
    val validMarkings = List("X", "O");

    def ==(other: Cell):Boolean = {
        return(this.getMarking() == other.getMarking());
    }

    def getMarking():String = {
        return this.marking;
    }

    def isMarked():Boolean = {
        return(this.getMarking() != "");
    }

    def isValid(mark: String):Boolean = {
        return(this.validMarkings.contains(mark));
    }

    def setMark(otherMark: String):Unit = {
        if(this.isMarked()){
            throw new IllegalStateException("This cell is already marked.");
        }
        if(!this.isValid(otherMark)){
            throw new IllegalArgumentException("This marking is not valid: " + otherMark);
        }

        this.marking = otherMark;
    }

    override def toString():String = {
        if(this.isMarked()){
            return(this.getMarking());
        } else{
            return(" ");
        }
    }
}

class Board{
    val board = List(
        new Cell(),
        new Cell(),
        new Cell(),
        new Cell(),
        new Cell(),
        new Cell(),
        new Cell(),
        new Cell(),
        new Cell()
    );

    val winConditions = List(
        List(0, 1, 2),
        List(3, 4, 5),
        List(6, 7, 8),
        List(0, 4, 8),
        List(2, 4, 6),
        List(0, 3, 6),
        List(1, 4, 7),
        List(2, 5, 8)
    );

    def getWinner():String = {
        this.winConditions.foreach(cond =>
            if(this.pathWins(cond)){
                return(this.board(cond(0)).getMarking());
            }
        );

        return("I am error");
    }

    def hasFreeCells():Boolean = {
        this.board.foreach( cell =>
            if(!cell.isMarked()){
                return(true);
            }
        );

        return(false);
    }

    def hasMoves():Boolean = {
        return(this.hasFreeCells() && !this.hasWinner());
    }

    def pathWins(path: List[Int]):Boolean = {
        val cell1 = this.board(path(0));
        val cell2 = this.board(path(1));
        val cell3 = this.board(path(2));

        return(cell1.isMarked() && (cell1 == cell2) && (cell1 == cell3));
    }

    def hasWinner():Boolean = {
        this.winConditions.foreach(cond =>
            if(this.pathWins(cond)){
                return(true);
            }
        );

        return(false);
    }

    def isTied():Boolean = {
        return(!hasFreeCells() && !hasWinner());
    }

    def setMark(cell: Int, mark: String) = {
        this.board(cell).setMark(mark);
    }

    override def toString():String = {
        return(
            this.board(0) + "|" +
            this.board(1) + "|" +
            this.board(2) + "\n" +
            "-----\n" +
            this.board(3) + "|" +
            this.board(4) + "|" +
            this.board(5) + "\n" +
            "-----\n" +
            this.board(6) + "|" +
            this.board(7) + "|" +
            this.board(8) + "\n"
        );
    }
}

class Game{
    this.play();

    def getNextPlayer(current: String):String = {
        if(current == "X"){
            return("O");
        } else{
            return("X");
        }
    }

    def play():Unit = {
        var board = new Board();
        var currentPlayer = "X";

        print("\n");
        while(board.hasMoves()){
            println(board);
            print("\n" + currentPlayer + ", enter cell index you'd like to mark (1-9): ");

            try{
                var cell = Console.readInt();
                board.setMark(cell - 1, currentPlayer);
                currentPlayer = this.getNextPlayer(currentPlayer);

            } catch{
                case e:Exception =>
                    println("Invalid cell index");
            }
            print("\n");
        }

        println(board);

        if(board.isTied()){
            println("Tie");
        } else{
            var winner = board.getWinner();

            println(winner + " wins.");
        }
    }
}

var game = new Game();
