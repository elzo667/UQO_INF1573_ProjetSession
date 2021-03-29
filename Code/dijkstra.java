package Code;

/**
 * Nom: Dijkstra
 * Version: 1.0
 * Date: 03/29/2021
 * Auteur: Membres de l'�quipe 4
 * Copyright 2021 �quipe 4
 * La classe Dijkstra impl�mente l'algorithme du
 * m�me nom permettant de trouver le chemin le 
 * plus cours entre deux noeuds dans un graphe.
 * Ce programme utilise un graphe sous un format
 * matriciel.
 */

import java.util.ArrayList;
import java.util.Arrays;

class Dijkstra {

	/**
	 * Constantes et variables globales
	 */
    private static final int AUCUN_PARENT = -1;
    
    private static int[] distancesMin;
    private static int[] parents;
    private static ArrayList<Integer> route = new ArrayList<Integer>();
    
    /**
     * Pr�sente sous forme d'un tableau d'entiers le chemin le plus court 
     * entre un noeud de d�part et un noeud d'arriv�e.
     * @param grapheMatrice : Une matrice en deux dimensions repr�sentant un graphe
     * @param noeudDepart : Le noeud d'o� commencera le chemin
     * @param noeudArrivee : Le noeud o� finira le chemin
     * @return un tableau d'entiers contenant le chemin le plus court
     */
    public static int [] cheminASuivre(int[][] grapheMatrice, int noeudDepart, int noeudArrivee) {
    	
    	dijkstra(grapheMatrice, noeudDepart);
    	
    	route = getChemin(noeudArrivee, parents);
    	
    	int [] chemin = new int [route.size()];
    	
    	for(int i = 0; i < route.size(); i++) {
    		chemin[i] = route.get(i);
    	}
    	
    	return chemin;
    }
    
    /**
     * Tire du tableau parent le chemin le plus court
     * @param noeudActuel : Le noeud �valu�
     * @param parents : Le tableau contenant l'ensemble des chemins
     * @return un ArrayList contenant le chemin le plus court du noeud
     * de d�part au noeud d'arriv�e
     */
	private static ArrayList<Integer> getChemin(int noeudActuel, int[] parents) {
	    	
	    	// Base case : Source node has
	        // been processed
	        if (noeudActuel == AUCUN_PARENT) {
	            return route;
	        } else {
	        	route = getChemin(parents[noeudActuel], parents);
	            route.add(noeudActuel);
	        }
			return route;
	}
	
	/**
	 * Permet d'acc�der � la distance que repr�sente le chemin le
	 * plus court
	 * @param grapheMatrice : Une matrice en deux dimensions repr�sentant un graphe
	 * @param noeudDepart : Le noeud d'o� commence le chemin
	 * @param noeudArrivee : Le noeud o� finit le chemin
	 * @return La distance parcourue sous forme d'un entier
	 */
	public static int distanceMin(int[][] grapheMatrice, int noeudDepart, int noeudArrivee) {
	    	
	    	dijkstra(grapheMatrice, noeudDepart);
	    	return distancesMin[noeudArrivee];
	}
    
    /**
     * Calcule, � partir d'un point de d�part, le chemin le plus court vers chacun 
     * des noeuds du graphe, ainsi que la distance qu'il repr�sente. Stocke ces
     * r�sultats dans les variables globales parents et distancesMin respectivement.
     * @param grapheMatrice : Une matrice en deux dimensions repr�sentant un graphe
     * @param noeudDepart : Le noeud d'o� commencera le calcul des chemins
     */
    private static void dijkstra(int[][] grapheMatrice, int noeudDepart) {
    	
        int nbNoeuds = grapheMatrice[0].length;

        /*
         * Contiendra les distances les plus courtes de
         * noeudDepart � chacun des noeuds d'arriv�e
         */
        distancesMin = new int[nbNoeuds];

        /*
         * ajoute[i] sera true si le noeud i � �t� �valu� et 
         * ajout� � l'arbre de chemins possibles
         */
        boolean[] ajoute = new boolean[nbNoeuds];

        /*
         * Initialise toutes les distances � la plus grande
         * valeur possible et tout le tableau ajoute � false
         */
        for (int noeudIndex = 0; noeudIndex < nbNoeuds; noeudIndex++)
        {
            distancesMin[noeudIndex] = Integer.MAX_VALUE;
            ajoute[noeudIndex] = false;
        }

        /*
         * La distance entre le noeud de d�part et lui-m�me
         * est toujours de 0.
         */
        distancesMin[noeudDepart] = 0;

        // Contient l'arbre des plus courts chemins
        parents = new int[nbNoeuds];

        // Le noeud de d�part n'a pas de parent
        parents[noeudDepart] = AUCUN_PARENT;

        // Trouve le plus court chemin pour chaque noeud d'arriv�e
        for (int i = 1; i < nbNoeuds; i++)
        {

        	/*
        	 * Choisi le noeud le plus pr�s parmi les noeuds
        	 * qui n'ont pas encore �t� �valu�s. noeudVoisin est 
        	 * toujours le noeud de d�part � la premi�re it�ration.
        	 */
            int noeudVoisin = -1;
            int distanceMin = Integer.MAX_VALUE;
            for (int noeudIndex = 0; noeudIndex < nbNoeuds; noeudIndex++)
            {
                if (!ajoute[noeudIndex] && distancesMin[noeudIndex] < distanceMin)
                {
                    noeudVoisin = noeudIndex;
                    distanceMin = distancesMin[noeudIndex];
                }
            }

            // Marque le noeud choisi comme �valu�
            ajoute[noeudVoisin] = true;

            /*
             * Met � jour la distance des noeuds voisins en
             * fonction du noeud choisi.
             */
            for (int noeudIndex = 0; noeudIndex < nbNoeuds; noeudIndex++)
            {
                int routeLongueur = grapheMatrice[noeudVoisin][noeudIndex];

                if (routeLongueur > 0 && ((distanceMin + routeLongueur) < distancesMin[noeudIndex]))
                {
                    parents[noeudIndex] = noeudVoisin;
                    distancesMin[noeudIndex] = distanceMin + routeLongueur;
                }
            }
        }
    }

    
    // Main pour tester
    public static void main(String[] args)
    {
        int[][] adjacencyMatrix = { { 0, 5, 0, 0, 0, 1, 2, 0},
                { 5, 0, 1, 0, 0, 0, 3, 0},
                { 0, 1, 0, 6, 0, 0, 0, 3},
                { 0, 0, 6, 0, 12, 0, 0, 6},
                { 0, 0, 0, 12, 0, 3, 0, 4},
                { 1, 0, 0, 0, 3, 0, 1, 0},
                { 2, 3, 0, 0, 0, 1, 0, 10},
                { 0, 0, 3, 6, 4, 0, 10, 0}};

        //dijkstra(adjacencyMatrix, 0);
        int distance0_3 = distanceMin(adjacencyMatrix, 1, 4);
        System.out.println(distance0_3);
        int[] chemin0_3 = cheminASuivre(adjacencyMatrix, 1, 4);
        System.out.println(Arrays.toString(chemin0_3));
    }
}

// Ce code a �t� inspir� de Harikrishnan Rajan
