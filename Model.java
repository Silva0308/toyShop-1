import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Model implements ProgrammFunction{

    @Override
    public void playLottery(){
    }

    @Override
    public void changeWightChance() {
    }

    @Override
    public void addToy() {

        Toy toy=new Toy(Integer.parseInt(JOptionPane.showInputDialog(null,
                "Введите id: ",
                "Добавление игрушки",
                JOptionPane.QUESTION_MESSAGE)),
                JOptionPane.showInputDialog(null,
                        "Введите наименование: ",
                        "Добавление игрушки",
                        JOptionPane.QUESTION_MESSAGE),
                Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Введите количество:",
                        "Добавление игрушек",
                        JOptionPane.QUESTION_MESSAGE)),
                Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Введите вес в %: ",
                        "Добавление игрушки",
                        JOptionPane.QUESTION_MESSAGE)));
        try {
            File path=new File("TOYWAREHOUSE.txt");

            if (!path.exists()){
                path.createNewFile();
            }

            BufferedWriter bw=new BufferedWriter(new FileWriter(path,true));

            bw.write(toy.getAllInfo());

            bw.newLine();

            JOptionPane.showMessageDialog(null,
                    "Игрушка: \nid: "
                            +toy.getId()+"\nназвание: "
                            +toy.getToyName()+"\nколичество: "
                            +toy.getCountOfToy()+"\nвес: "
                            +toy.getWeigthChance()+"\nуспешно добавлена",
                    "Сообщение",
                    JOptionPane.INFORMATION_MESSAGE);

            bw.close();
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null,e,"Ошибка",JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void getToy(){
        int idUser = Integer.parseInt(JOptionPane.showInputDialog(null,
        "Введите Id участника: ",
        "Выдача игрушки",
        JOptionPane.INFORMATION_MESSAGE));
        String str;
        try {
            File path = new File("PRIZE-TOY.txt");
            BufferedReader br = new BufferedReader(new FileReader(path));
            ArrayList<String> priorityArray = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                priorityArray.add(str);

            }
            br.close();

            for (int j = 0; j < priorityArray.size(); j++) {
                String[] secondArray = new String[3];
                secondArray = priorityArray.get(j).split(";");
                if (idUser == Integer.parseInt(secondArray[2])) {
                    JOptionPane.showMessageDialog(null,
                            "Поздравляем ваш выйгрыш\n" + secondArray[0] + "," + secondArray[1],
                            "Выдача игрушки",
                            JOptionPane.INFORMATION_MESSAGE);

                    priorityArray.remove(j);
                }
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (int k = 0; k < priorityArray.size(); k++) {
                bw.write(priorityArray.get(k));
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
