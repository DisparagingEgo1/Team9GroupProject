package cs350s22.component.ui.parser;

public class Command {
    private final String[] commandText;
    private int tokenIndex;

    public Command(final String[] commandText) {
        this.commandText = commandText;
        this.tokenIndex = 0;
    }

    /**
     * @return The next token
     * @throws ArrayIndexOutOfBoundsException If the next token does not exist
     */
    public String getNext() throws ArrayIndexOutOfBoundsException {
        return commandText[tokenIndex++];
    }

    /**
     * Returns the token from commandText at the specified index.
     *
     * @param index Index of token to retrieve
     * @return The token at index
     * @throws ArrayIndexOutOfBoundsException If the index is out of bounds
     */
    public String tokenAt(int index) throws ArrayIndexOutOfBoundsException {
        return commandText[index];
    }

    public boolean isParsed() {
        return tokenIndex == commandText.length;
    }

    public int length() {
        return commandText.length;
    }
    public boolean hasNext(){return this.tokenIndex< commandText.length;}
    public boolean equalsNext(String toCompare){
        return this.commandText[this.tokenIndex].equalsIgnoreCase(toCompare);
    }
    public boolean equalsNext(String[] toCompare){
        for(String s: toCompare){
            if(this.equalsNext(s))return true;
        }
        return false;
    }
    public int getTokenIndex(){return this.tokenIndex;}
    public boolean isNumeric(String candidate){
        try{
            Double.parseDouble(candidate);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
}
