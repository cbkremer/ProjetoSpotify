/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.Musics;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Carlos Kremer
 */
@Stateless
public class MusicsFacade extends AbstractFacade<Musics> {

    @PersistenceContext(unitName = "ProjetoSpotifyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MusicsFacade() {
        super(Musics.class);
    }
    
}
