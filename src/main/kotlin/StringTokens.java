import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class StringTokens {
    public static void main(String[] args) {
        StringTokenizer T = new StringTokenizer("ТОО.КамПРЗ.КАМАЗ",".");
        String TokenGroup = "";
        int count_Tokens = T.countTokens();
        while ( count_Tokens-2 > 0) {
            T.nextToken();
            count_Tokens--;
        }
        TokenGroup = T.nextToken().toString();
        System.out.println(TokenGroup);
    }
}

/*class StringTokenizer extends Object implements Enumeration<Object> {
    private int currentPosition = 0;

    private int newPosition = -1;

    private int maxPosition;

    private String str;

    private String delimiters;

    private boolean retDelims;

    private boolean delimsChanged = false;

    private int maxDelimCodePoint;

    private boolean hasSurrogates = false;

    private int[] delimiterCodePoints;

    private void setMaxDelimCodePoint() {
        if (this.delimiters == null) {
            this.maxDelimCodePoint = 0;
            return;
        }
        int i = 0;
        byte b = 0;
        int j;
        int k = this.delimiters.charAt(0);
        for (j = 0; j < this.delimiters.length(); j += Character.charCount(k)) {
            k = this.delimiters.charAt(0);
            if (k >= 55296 && k <= 57343) {
                k = this.delimiters.codePointAt(j);
                this.hasSurrogates = true;
            }
            if (i < k)
                i = k;
            b++;
        }
        this.maxDelimCodePoint = i;
        if (this.hasSurrogates) {
            this.delimiterCodePoints = new int[b];
            j = 0;
            k=0;
            int m = this.delimiters.codePointAt(k);
            for (k = 0; j < b; k += Character.charCount(m)) {
                m = this.delimiters.codePointAt(k);
                this.delimiterCodePoints[j] = m;
                j++;
            }
        }
    }

    public StringTokenizer(String paramString1, String paramString2, boolean paramBoolean) {
        this.str = paramString1;
        this.maxPosition = paramString1.length();
        this.delimiters = paramString2;
        this.retDelims = paramBoolean;
        setMaxDelimCodePoint();
    }

    public StringTokenizer(String paramString1, String paramString2) { this(paramString1, paramString2, false); }

    public StringTokenizer(String paramString) { this(paramString, " \t\n\r\f", false); }

    private int skipDelimiters(int paramInt) {
        if (this.delimiters == null)
            throw new NullPointerException();
        int i;
        int j = this.str.codePointAt(paramInt);
        for (i = paramInt; !this.retDelims && i < this.maxPosition; i += Character.charCount(j)) {
            if (!this.hasSurrogates) {
                char c = this.str.charAt(i);
                if (c > this.maxDelimCodePoint || this.delimiters.indexOf(c) < 0)
                    break;
                i++;
                continue;
            }
            j = this.str.codePointAt(i);
            if (j > this.maxDelimCodePoint || !isDelimiter(j))
                break;
        }
        return i;
    }

    private int scanToken(int paramInt) {
        int i;
        int j = this.str.codePointAt(paramInt);
        for (i = paramInt; i < this.maxPosition; i += Character.charCount(j)) {
            if (!this.hasSurrogates) {
                char c = this.str.charAt(i);
                if (c <= this.maxDelimCodePoint && this.delimiters.indexOf(c) >= 0)
                    break;
                i++;
                continue;
            }
            j = this.str.codePointAt(i);
            if (j <= this.maxDelimCodePoint && isDelimiter(j))
                break;
        }
        if (this.retDelims && paramInt == i)
            if (!this.hasSurrogates) {
                char c = this.str.charAt(i);
                if (c <= this.maxDelimCodePoint && this.delimiters.indexOf(c) >= 0)
                    i++;
            } else {
                j = this.str.codePointAt(i);
                if (j <= this.maxDelimCodePoint && isDelimiter(j))
                    i += Character.charCount(j);
            }
        return i;
    }

    private boolean isDelimiter(int paramInt) {
        for (byte b = 0; b < this.delimiterCodePoints.length; b++) {
            if (this.delimiterCodePoints[b] == paramInt)
                return true;
        }
        return false;
    }

    public boolean hasMoreTokens() {
        this.newPosition = skipDelimiters(this.currentPosition);
        return (this.newPosition < this.maxPosition);
    }

    public String nextToken() {
        this.currentPosition = (this.newPosition >= 0 && !this.delimsChanged) ? this.newPosition : skipDelimiters(this.currentPosition);
        this.delimsChanged = false;
        this.newPosition = -1;
        if (this.currentPosition >= this.maxPosition)
            throw new NoSuchElementException();
        int i = this.currentPosition;
        this.currentPosition = scanToken(this.currentPosition);
        if (this.currentPosition>str.length()) { this.currentPosition=str.length();}
        return this.str.substring(i, this.currentPosition);
    }

    public String nextToken(String paramString) {
        this.delimiters = paramString;
        this.delimsChanged = true;
        setMaxDelimCodePoint();
        return nextToken();
    }

    public boolean hasMoreElements() { return hasMoreTokens(); }

    public Object nextElement() { return nextToken(); }

    public int countTokens() {
        byte b = 0;
        int i = this.currentPosition;
        while (i < this.maxPosition) {
            i = skipDelimiters(i);
            if (i >= this.maxPosition)
                break;
            i = scanToken(i);
            b++;
        }
        return b;
    }
}*/
