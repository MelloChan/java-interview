package base.serializable;

import java.io.*;
import java.util.Date;

/**
 * transient 字段
 * Created by MelloChan on 2018/2/13.
 */
public class Login implements Serializable {

    private static final long serialVersionUID = -1218878082545441950L;

    private Date date = new Date();
    private String username;
    private transient String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Login login = new Login("mello", "password");
        System.out.println(login);

        ObjectOutput out = new ObjectOutputStream(
                new FileOutputStream("login.out")
        );
        System.out.println("Saving objects:");
        out.writeObject(login);
        out.close();

        ObjectInput in=new ObjectInputStream(
                new FileInputStream("login.out")
        );
        System.out.println("Recovering objects:");
        login= (Login) in.readObject();
        System.out.println(login);
    }

    @Override
    public String toString() {
        return "Login{" +
                "date=" + date +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}/* output:
Login{date=Tue Feb 13 22:13:02 CST 2018, username='mello', password='password'}
Saving objects:
Recovering objects:
Login{date=Tue Feb 13 22:13:02 CST 2018, username='mello', password='null'}
*/
