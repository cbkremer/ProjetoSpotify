/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TESTE;

import bean.AbstractFacade;
import bean.MusicsFacade;
import controller.MusicsController;
import dao.Musics;

/**
 *
 * @author cbkremer
 */
public class Teste {
    public static void main(String[] args){
        Musics musics = new Musics();
        MusicsController mc = new MusicsController();
        System.out.println(mc.getItemsAvailableSelectMany());
        
        
    }
}
