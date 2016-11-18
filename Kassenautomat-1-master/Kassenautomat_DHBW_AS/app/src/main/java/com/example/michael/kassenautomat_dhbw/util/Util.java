package com.example.michael.kassenautomat_dhbw.util;

import com.example.michael.kassenautomat_dhbw.datatypes.Automata;
import com.example.michael.kassenautomat_dhbw.datatypes.Money;
import com.example.michael.kassenautomat_dhbw.datatypes.User;
import com.example.michael.kassenautomat_dhbw.exceptions.DbException;

/**
 * Created by Michael on 28.06.2016.
 */
public class Util {

    static public void giveNickelback(int payback, KassenautomatContext kassenautomatContext) {

        System.out.println("Give nickelback");
        System.out.println(payback);

        User user = kassenautomatContext.getCurrentUser();
        Money userMoney = user.getMoney();
        userMoney.print();

        Automata automata = kassenautomatContext.getAutomata();
        Money automataMoney = automata.getMoney();

        int nickelbackIndex = 0;
        while(payback < 0 && nickelbackIndex <= 6) {
            if(automataMoney.getAmountOfCoinValue(Money.coinValues[nickelbackIndex]) <= 0) {
                nickelbackIndex++;
                continue;
            }
            if(Money.coinValues[nickelbackIndex] <= Math.abs(payback)) {
                System.out.println("Give back " + Money.coinValues[nickelbackIndex]);
                System.out.println(payback);
                userMoney.add(Money.coinValues[nickelbackIndex]);
                automataMoney.remove(Money.coinValues[nickelbackIndex]);
                payback += Money.coinValues[nickelbackIndex];
                System.out.println(payback);
            } else {
                nickelbackIndex++;
            }
        }

        if(payback < 0) {
            nickelbackIndex = 5;
            while(payback < 0 && nickelbackIndex >= 0) {
                nickelbackIndex--;
                if(automataMoney.getAmountOfCoinValue(Money.coinValues[nickelbackIndex]) <= 0){
                    continue;
                }

                userMoney.add(Money.coinValues[nickelbackIndex]);
                automataMoney.remove(Money.coinValues[nickelbackIndex]);
                break;
            }
        }

        user.setMoney(userMoney);
        automata.setMoney(automataMoney);

        try {
            kassenautomatContext.updateCurrentUser(user);
            kassenautomatContext.updateAutomata(automata);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
