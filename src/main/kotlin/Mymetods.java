import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

class SomeClass {
    public static int i=1;
}
public class Mymetods {
    ///В этом классе будут записаны все оригинальные методы

    public static  void main(String[] args ) {
        System.out.print(getStudentRating("Анна,Математика,85;Анна,Химия,90;Борис,Математика,75;Борис,История,80;Евгений,Математика,95;Евгений,История,85","Математика,80"));

    }
    static public String getStudentRating(String studentData, String courseInfo) {
        String[] studentMas = studentData.split(";");
        String[][] list = new String[studentMas.length][];
        for (int i=0;i<studentMas.length;i++) {
            String[] dateOne = studentMas[i].split(",");
            list[i] = dateOne;
        }
        String[] predmet = courseInfo.split(",");

        String ret = "";
        String[][] listRet = new String[0][];
        listRet = sort2(list);
        for (int i=0;i<list.length;i++) {
            String[] dateOne = list[i];
            int ballSt = Integer.parseInt(dateOne[2]);
            int ballM = Integer.parseInt(predmet[1]);
            if (dateOne[1].equals(predmet[0])&&ballSt>ballM) {
                ret+=listRet[i][0]+","+listRet[i][2]+"\n";
            }
        }
        return ret;
    }

    static String[][] sort2(String[][] mas) {
        for (int i=0;i<mas.length-1;i++) {
            int min = Integer.parseInt(mas[i][2]);
            int im=0;
            for (int j=0;j< mas.length;j++) {
                int ball = Integer.parseInt(mas[i][2]);
                if (ball>min) {min = ball;im=j;}
            }
            String[] b = mas[im];
            mas[im] = mas[i];
            mas[i] = b;
        }
        return mas;
    }


    public static String generateReport(String salesData) {
        String[] oper = salesData.split(";");
        String[][] list = new String[4][];
        for (int i=0;i< oper.length;i++) {
            String[] p = oper[i].split(":");
            String[] date = p[0].split("-");
            int m = Integer.parseInt(date[1]),nK=(m-1)/3;
            String[] dK = list[nK];
            if (dK==null) {
                dK=new String[1];
                dK[0] = "- "+p[1]+": "+p[2];
            }
            else {
                boolean r = true;
                for (int j=0;j<dK.length;j++) {
                    String[] dKc = dK[j].split(" ");
                    //System.out.println(String.join("_",dKc)+"   "+p[1]);
                    if (dKc[1].equals(p[1]+":")) {
                        dK[j] = "- "+p[1]+": "+(Integer.parseInt(dKc[2])+Integer.parseInt(p[2]));
                        r=false;
                    }
                }
                if (r) {
                    dK = copyPlus(dK);
                    dK[dK.length-1] = "- "+p[1]+": "+p[2];
                }
            }
            list[nK] = dK;
        }
        String ret = "";
        for (int i=0;i<list.length;i++) {
            String[] dK = list[i];
            if (dK!=null) {dK = sortMas(dK);
            list[i] = dK;

            ret+="Q"+(i+1)+"\n"+String.join("\n",list[i]);
            if (i<list.length-1) {ret+="\n";}
            }
        }

        return ret;
    }

    static public String[] sortMas(String[] mas) {
        for (int i=0;i<mas.length-1;i++) {
            String min = mas[i]; int im=0;
            for (int j=i+1; j<mas.length;j++) {
                if (min.compareTo(mas[j])>0) {min = mas[j];im=j;}
            }
            String b = mas[im];
            mas[im] = mas[i];
            mas[i] = b;
        }
        return mas;
    }
    static public String[] copyPlus(String[] mas) {
        String[] mas2 =new String[mas.length+1];
        for (int i=0;i<mas.length;i++) {
            mas2[i] = mas[i];
        }
        return mas2;
    }

    public static class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String row1, String row2) {
            return row1.compareTo(row2);
        }
    }

    //Метод который может вычислять длину строки с учетом ширины букв
    public static float textLength(String text) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = new Font("Tahoma", Font.PLAIN, 12);
        float textwidth = (float)(font.getStringBounds(text, frc).getWidth());
        float textheight = (float)(font.getStringBounds(text, frc).getHeight());
        return textwidth;
    }

    public static int MySizeSimvole(char c) {
        Map<Character, Integer> sizeSim = new HashMap<Character, Integer>();
        sizeSim.put(' ', 35);sizeSim.put('q', 62);sizeSim.put('w', 82);sizeSim.put('e', 60);sizeSim.put('r', 43);sizeSim.put('t', 40);sizeSim.put('y', 60);sizeSim.put('u', 63);sizeSim.put('i', 27);sizeSim.put('o', 61);sizeSim.put('p', 63);sizeSim.put('[', 45);sizeSim.put(']', 45);
        sizeSim.put('a', 60);sizeSim.put('s', 52);sizeSim.put('d', 62);sizeSim.put('f', 35);sizeSim.put('g', 62);sizeSim.put('h', 63);sizeSim.put('j', 34);sizeSim.put('k', 59);sizeSim.put('l', 27);sizeSim.put(';', 45);sizeSim.put('\'', 27);sizeSim.put('\\', 45);
        sizeSim.put('z', 53);sizeSim.put('x', 59);sizeSim.put('c', 52);sizeSim.put('v', 59);sizeSim.put('b', 62);sizeSim.put('n', 63);sizeSim.put('m', 97);sizeSim.put(',', 36);sizeSim.put('.', 36);sizeSim.put('/', 45);sizeSim.put('`', 64);sizeSim.put('1', 64);
        sizeSim.put('2', 64);sizeSim.put('3', 64);sizeSim.put('4', 64);sizeSim.put('5', 64);sizeSim.put('6', 64);sizeSim.put('7', 64);sizeSim.put('8', 64);sizeSim.put('9', 64);sizeSim.put('0', 64);sizeSim.put('-', 45);sizeSim.put('=', 82);sizeSim.put('*', 64);
        sizeSim.put('_', 64);sizeSim.put('+', 82);sizeSim.put('Q', 79);sizeSim.put('W', 99);sizeSim.put('E', 63);sizeSim.put('R', 70);sizeSim.put('T', 62);sizeSim.put('Y', 62);sizeSim.put('U', 73);sizeSim.put('I', 42);sizeSim.put('O', 79);sizeSim.put('P', 61);
        sizeSim.put('A', 69);sizeSim.put('S', 68);sizeSim.put('D', 77);sizeSim.put('F', 57);sizeSim.put('G', 78);sizeSim.put('H', 75);sizeSim.put('J', 45);sizeSim.put('K', 69);sizeSim.put('L', 56);sizeSim.put('Z', 69);sizeSim.put('X', 69);sizeSim.put('C', 70);
        sizeSim.put('V', 69);sizeSim.put('B', 69);sizeSim.put('N', 75);sizeSim.put('M', 84);sizeSim.put('~', 82);sizeSim.put('{', 64);sizeSim.put('}', 64);sizeSim.put(':', 46);sizeSim.put('"', 46);sizeSim.put('|', 46);sizeSim.put('<', 82);sizeSim.put('>', 82);
        sizeSim.put('?', 55);sizeSim.put('й', 64);sizeSim.put('ц', 65);sizeSim.put('у', 59);sizeSim.put('к', 59);sizeSim.put('е', 60);sizeSim.put('н', 64);sizeSim.put('г', 47);sizeSim.put('ш', 88);sizeSim.put('щ', 89);sizeSim.put('з', 53);sizeSim.put('х', 59);
        sizeSim.put('ъ', 64);sizeSim.put('ф', 84);sizeSim.put('ы', 80);sizeSim.put('в', 60);sizeSim.put('а', 60);sizeSim.put('п', 64);sizeSim.put('р', 63);sizeSim.put('о', 61);sizeSim.put('л', 62);sizeSim.put('д', 62);sizeSim.put('ж', 80);sizeSim.put('э', 55);
        sizeSim.put('я', 60);sizeSim.put('ч', 61);sizeSim.put('с', 54);sizeSim.put('м', 70);sizeSim.put('и', 64);sizeSim.put('т', 50);sizeSim.put('ь', 57);sizeSim.put('б', 62);sizeSim.put('ю', 84);sizeSim.put('ё', 60);sizeSim.put('!', 39);sizeSim.put('№', 117);
        sizeSim.put('%', 108);sizeSim.put('(', 46);sizeSim.put(')', 46);sizeSim.put('@', 100);sizeSim.put('#', 82);sizeSim.put('$', 64);sizeSim.put('^', 82);sizeSim.put('&', 73);sizeSim.put('Й', 75);sizeSim.put('Ц', 72);sizeSim.put('У', 62);sizeSim.put('К', 69);
        sizeSim.put('Е', 63);sizeSim.put('Н', 75);sizeSim.put('Г', 57);sizeSim.put('Ш', 103);sizeSim.put('Щ', 105);sizeSim.put('З', 62);sizeSim.put('Х', 69);sizeSim.put('Ъ', 79);sizeSim.put('Ф', 82);sizeSim.put('Ы', 92);sizeSim.put('В', 69);sizeSim.put('А', 68);
        sizeSim.put('П', 75);sizeSim.put('Р', 60);sizeSim.put('О', 79);sizeSim.put('Л', 74);sizeSim.put('Д', 75);sizeSim.put('Ж', 98);sizeSim.put('Э', 70);sizeSim.put('Я', 71);sizeSim.put('Ч', 71);sizeSim.put('С', 70);sizeSim.put('М', 84);sizeSim.put('И', 75);
        sizeSim.put('Т', 62);sizeSim.put('Ь', 68);sizeSim.put('Б', 69);sizeSim.put('Ю', 103);sizeSim.put('Ё', 63);

        int i =0; boolean out=false;
        for (Map.Entry<Character, Integer> entry : sizeSim.entrySet()) {
            Character innerKey = entry.getKey();
            if (innerKey==c) {
                i = entry.getValue();
                out=true;
                break;
            }
        }
        return i;
    }

    public static int MySizeString(String str) {
        int length=0;
        for (int i=0; i<str.length();i++) {
            char c = str.charAt(i);
            length+=MySizeSimvole(c);
        }
        return length;
    }
///В этом классе есть метод который компанует строку на List состоящий из просто текста и [][][][]
    static class kompanovkaClass {
        public List<Revision> kompanovkaKubik(String str) {
            List<Revision> list = new ArrayList<>();
            boolean begin = false, close = false;
            int n = 0, k = 0, k2 = 0;
            ;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '[') {
                    if (i == 0 || (i > 0) && (str.charAt(i - 1) != ']' || close) && (!begin)) {
                        Revision r = new Revision(str.substring(k, i), "t");
                        list.add(r);
                        n = i;
                        begin = true;
                    } else if ((i > 0) && (str.charAt(i - 1) != ']') && (begin)) {
                        list.remove(list.size() - 1);
                        if (k2 > n) {
                            Revision r = new Revision(str.substring(k, n), "t");
                            list.add(r);
                            Revision r2 = new Revision(str.substring(n, k2 + 1), "d");
                            list.add(r2);
                            Revision r3 = new Revision(str.substring(k2 + 1, i), "t");
                            list.add(r3);
                            begin = false;
                            k = i;
                            i--;
                        } else {
                            Revision r = new Revision(str.substring(k, i), "t");
                            list.add(r);
                            n = i;
                        }

                    }
                    close = false;
                } else if (str.charAt(i) == ']' && begin) { // &&i==(str.length()-1)||(i<(str.length()-1))&&str.charAt(i)==']'&&str.charAt(i)!='['
                    if (i == (str.length() - 1) || (i < (str.length() - 1)) && str.charAt(i + 1) != '[') {
                        Revision r = new Revision(str.substring(n, i + 1), "d");
                        list.add(r);
                        k = i + 1;
                        begin = false;
                        close = true;
                    } else {
                        k2 = i;
                    }
                }
            }
            if (k < str.length()) {
                Revision r = new Revision(str.substring(k), "t");
                list.add(r);
            }
            int i = 0;
            while (i < list.size()) {
                if (list.get(i).getName().length() == 0) {
                    list.remove(i);
                } else {
                    i++;
                }
            }
            if (list.size() == 0) {
                return list;
            }

            String teg = list.get(0).getIden();
            i = 1;
            while (i < list.size()) {
                if (list.get(i).getIden().equals(teg)) {
                    String name2 = list.get(i - 1).getName() + list.get(i).getName();
                    list.set(i - 1, new Revision(teg, name2));
                    list.remove(i);
                } else {
                    teg = list.get(i).getIden();
                    i++;
                }
            }
            return list;
        }

        public class Revision {
            String name, identiF;

            public Revision(String name0, String identiF0) {
                name = name0;
                identiF = identiF0;
            }

            public String getName() {
                return name;
            }

            public String getIden() {
                return identiF;
            }
        }
    }

    class lengthAbsolutString {

        public int MySizeSimvole(char c) {
            Map<Character, Integer> sizeSim = new HashMap<Character, Integer>();
            sizeSim.put(' ', 35);sizeSim.put('q', 62);sizeSim.put('w', 82);sizeSim.put('e', 60);sizeSim.put('r', 43);sizeSim.put('t', 40);sizeSim.put('y', 60);sizeSim.put('u', 63);sizeSim.put('i', 27);sizeSim.put('o', 61);sizeSim.put('p', 63);sizeSim.put('[', 45);sizeSim.put(']', 45);
            sizeSim.put('a', 60);sizeSim.put('s', 52);sizeSim.put('d', 62);sizeSim.put('f', 35);sizeSim.put('g', 62);sizeSim.put('h', 63);sizeSim.put('j', 34);sizeSim.put('k', 59);sizeSim.put('l', 27);sizeSim.put(';', 45);sizeSim.put('\'', 27);sizeSim.put('\\', 45);
            sizeSim.put('z', 53);sizeSim.put('x', 59);sizeSim.put('c', 52);sizeSim.put('v', 59);sizeSim.put('b', 62);sizeSim.put('n', 63);sizeSim.put('m', 97);sizeSim.put(',', 36);sizeSim.put('.', 36);sizeSim.put('/', 45);sizeSim.put('`', 64);sizeSim.put('1', 64);
            sizeSim.put('2', 64);sizeSim.put('3', 64);sizeSim.put('4', 64);sizeSim.put('5', 64);sizeSim.put('6', 64);sizeSim.put('7', 64);sizeSim.put('8', 64);sizeSim.put('9', 64);sizeSim.put('0', 64);sizeSim.put('-', 45);/*sizeSim.put('=', 82);*/sizeSim.put('*', 64);
            sizeSim.put('_', 64);sizeSim.put('+', 82);sizeSim.put('Q', 79);sizeSim.put('W', 99);sizeSim.put('E', 63);sizeSim.put('R', 70);sizeSim.put('T', 62);sizeSim.put('Y', 62);sizeSim.put('U', 73);sizeSim.put('I', 42);sizeSim.put('O', 79);sizeSim.put('P', 61);
            sizeSim.put('A', 69);sizeSim.put('S', 68);sizeSim.put('D', 77);sizeSim.put('F', 57);sizeSim.put('G', 78);sizeSim.put('H', 75);sizeSim.put('J', 45);sizeSim.put('K', 69);sizeSim.put('L', 56);sizeSim.put('Z', 69);sizeSim.put('X', 69);sizeSim.put('C', 70);
            sizeSim.put('V', 69);sizeSim.put('B', 69);sizeSim.put('N', 75);sizeSim.put('M', 84);sizeSim.put('~', 82);sizeSim.put('{', 64);sizeSim.put('}', 64);sizeSim.put(':', 46);sizeSim.put('"', 46);sizeSim.put('|', 46);sizeSim.put('<', 82);sizeSim.put('>', 82);
            sizeSim.put('?', 55);sizeSim.put('й', 64);sizeSim.put('ц', 65);sizeSim.put('у', 59);sizeSim.put('к', 59);sizeSim.put('е', 60);sizeSim.put('н', 64);sizeSim.put('г', 47);sizeSim.put('ш', 88);sizeSim.put('щ', 89);sizeSim.put('з', 53);sizeSim.put('х', 59);
            sizeSim.put('ъ', 64);sizeSim.put('ф', 84);sizeSim.put('ы', 80);sizeSim.put('в', 60);sizeSim.put('а', 60);sizeSim.put('п', 64);sizeSim.put('р', 63);sizeSim.put('о', 61);sizeSim.put('л', 62);sizeSim.put('д', 62);sizeSim.put('ж', 80);sizeSim.put('э', 55);
            sizeSim.put('я', 60);sizeSim.put('ч', 61);sizeSim.put('с', 54);sizeSim.put('м', 70);sizeSim.put('и', 64);sizeSim.put('т', 50);sizeSim.put('ь', 57);sizeSim.put('б', 62);sizeSim.put('ю', 84);sizeSim.put('ё', 60);sizeSim.put('!', 39);sizeSim.put('№', 117);
            sizeSim.put('%', 108);sizeSim.put('(', 46);sizeSim.put(')', 46);sizeSim.put('@', 100);sizeSim.put('#', 82);sizeSim.put('$', 64);sizeSim.put('^', 82);sizeSim.put('&', 73);sizeSim.put('Й', 75);sizeSim.put('Ц', 72);sizeSim.put('У', 62);sizeSim.put('К', 69);
            sizeSim.put('Е', 63);sizeSim.put('Н', 75);sizeSim.put('Г', 57);sizeSim.put('Ш', 103);sizeSim.put('Щ', 105);sizeSim.put('З', 62);sizeSim.put('Х', 69);sizeSim.put('Ъ', 79);sizeSim.put('Ф', 82);sizeSim.put('Ы', 92);sizeSim.put('В', 69);sizeSim.put('А', 68);
            sizeSim.put('П', 75);sizeSim.put('Р', 60);sizeSim.put('О', 79);sizeSim.put('Л', 75);sizeSim.put('Д', 75);sizeSim.put('Ж', 98);sizeSim.put('Э', 70);sizeSim.put('Я', 71);sizeSim.put('Ч', 71);sizeSim.put('С', 70);sizeSim.put('М', 84);sizeSim.put('И', 75);
            sizeSim.put('Т', 62);sizeSim.put('Ь', 68);sizeSim.put('Б', 69);sizeSim.put('Ю', 103);sizeSim.put('Ё', 63);

            int i =0; boolean out=false;
            for (Map.Entry<Character, Integer> entry : sizeSim.entrySet()) {
                Character innerKey = entry.getKey();
                if (innerKey==c) {
                    i = entry.getValue();
                    out=true;
                    break;
                }
            }

            if (!out) {
                String s = ""+c;
                if (s.indexOf(String.valueOf((char) 9711))>=0) {i=150;} //круг+++
                else if (s.indexOf(String.valueOf((char) 8212))>=0) {i=150;} //линия горизонтальная+++
                else if (s.indexOf(String.valueOf((char) 9649))>=0) {i=180;} //параллелограмм+++
                else if (s.indexOf(String.valueOf((char) 9005))>=0) {i=160;} //круг между линиями под наклоном+++
                else if (s.indexOf(String.valueOf((char) 61))>=0) {i=100;} //равно+++
                else if (s.indexOf(String.valueOf((char) 8725))>=0) {i=90;} //наклонные две линии+++
                else if (s.indexOf(String.valueOf((char) 9162))>=0) {i=150;} //перпендикулярность+++
                else if (s.indexOf(String.valueOf((char) 10655))>=0) {i=150;} //угол+++
                else if (s.indexOf(String.valueOf((char) 9022))>=0) {i=150;} //круг в круге+++
                else if (s.indexOf(String.valueOf((char) 9007))>=0) {i=150;} //кнопка+++
                else if (s.indexOf(String.valueOf((char) 8982))>=0) {i=150;} //прицел+++
                else if (s.indexOf(String.valueOf((char) 10005))>=0) {i=150;} //пересечение
                else if (s.indexOf(String.valueOf((char) 10138))>=0) {i=150;} //стрелка+++
                else if (s.indexOf(String.valueOf((char) 9008))>=0) {i=150;} //две стрелки наклон+++
                else if (s.indexOf(String.valueOf((char) 8978))>=0) {i=180;} //дуга+++
                else if (s.indexOf(String.valueOf((char) 8979))>=0) {i=170;} //полуокружность+++//8241
                else if (s.indexOf(String.valueOf((char) 8241))>=0) {i=210;} ///%оо
                else {i=90;}
            }
            return i;
        }
        public int MySizeString(String str) {
            int length=0;
            for (int i=0; i<str.length();i++) {
                char c = str.charAt(i);
                length+=MySizeSimvole(c);
            }
            return length;
        }
    }
}


