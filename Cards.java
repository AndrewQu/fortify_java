import java.util.Random;

/**
 * represents a playing Card
 */
class Card {
    byte _rank;
    byte _suit;

    /**
    * Constructs an instance of Card
    * @suit - one of heart, spade, diamond, club, queen, king
    * @rank - 1 to 13
    */
    public Card(String suit, byte rank) throws IllegalArgumentException {
        if(suit == "heart") {
            _suit = 0;
        } else if(suit == "spade") {
            _suit = 1;
        } else if(suit == "diamond") {
            _suit = 2;
        } else if(suit == "club") {
            _suit = 3;
        } else if(suit == "queen") {
            _suit = 4;
        } else if(suit == "king") {
            _suit = 5;
        } else {
            throw new IllegalArgumentException("Not valid card suit name: " + suit);
        }
        if( rank > 0 && rank <= 13) _rank = rank;
        else throw new IllegalArgumentException("Card rank out of range (1 to 13): " + rank);
    }
    public String toString() {
        String rank = "" + _rank;
        if(_rank == 11) rank = "Jack";
        else if(_rank == 12) rank = "Queen";
        else if(_rank == 13) rank = "King";
        else if(_rank == 1) rank = "Ace";
        switch(_suit){
            case 0 : return "Heart " + rank;
            case 1 : return "Spade " + rank;
            case 2 : return "Diamond " + rank;
            case 3 : return "Club " + rank;
            case 4 : return "Queen";
            case 5 : return "King";
        }
        return "Error: suit=" + _suit;
    }
}

public class Cards {
    Card[] pack;
    int cards_dealt = 0;

    private static int numCards = 54;
    public Cards() {
        pack = new Card[numCards];
        for(int i=0; i<13; i++) {
            byte rank = (byte)(i+1);
            pack[4*i] = new Card("heart", rank);
            pack[4*i+1] = new Card("diamond", rank);
            pack[4*i+2] = new Card("spade", rank);
            pack[4*i+3] = new Card("club", rank);
        }
        pack[52] = new Card("queen", (byte)1);
        pack[53] = new Card("king", (byte)1);
    }
    public void Shuffle() {
        cards_dealt = 0;
        Random rand = new Random();
        for(int cycle=0; cycle < 2; cycle++) {
            for(int i=0; i<54; i++) {
                // Swap card i with random(i)
                int index = rand.nextInt(54);
                Card card_i = pack[i];
                pack[i] = pack[index];
                pack[index] = card_i;
            } 
        }
    }
    public Card DealNextCard() {
        if(cards_dealt == numCards) return null;
        return pack[cards_dealt++];
    }

    private void TestInnerClass() {
        System.out.println("**** TestInnerClass ***");
        CardGame game = this.new CardGame(2, 0);
        game.DealCards();
        game.ShowPlayerCard(0);
    }

    // Nested class
    class CardGame {
        int numPlayers;
        int numCardsPerPlayer;
        Card[][] cards;
        public CardGame(int nplayers, int cardsPerPlayer){
            numPlayers = nplayers;
            numCardsPerPlayer = cardsPerPlayer > 0 ?
                cardsPerPlayer : numCards / numPlayers;
            if(numPlayers * numCardsPerPlayer > numCards)
                throw new IllegalArgumentException("Invalid cardsPerPlayer in call to CardGame()");
            cards = new Card[nplayers][numCardsPerPlayer];
        }
        public void DealCards() {
            Shuffle();
            for(int i=0; i<numCardsPerPlayer; i++) {
                for(int p=0; p<numPlayers; p++) cards[p][i] = DealNextCard();
            }
        }
        public void ShowPlayerCard(int playIndex) {
            System.out.println("Player " + playIndex + " has " + numCardsPerPlayer + " cards:");
            for(int i=0; i<numCardsPerPlayer; i++) {
                System.out.println(cards[playIndex][i]);
            }

        }
    }

    public static void main(String args[]) {
        Cards packOfCards = new Cards();
        for(int i=0; i<54; i++)
            System.out.println("Card " + i + " : " + packOfCards.DealNextCard());
        packOfCards.Shuffle();
        System.out.println("******** Shuffled ********");
        for(int i=0; i<54; i++)
            System.out.println("Card " + i + " : " + packOfCards.DealNextCard());

        Cards.CardGame game = packOfCards.new CardGame(4, 0);
        game.DealCards();
        game.ShowPlayerCard(1);
        packOfCards.TestInnerClass();
    }
}

