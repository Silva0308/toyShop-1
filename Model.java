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

    }
}
