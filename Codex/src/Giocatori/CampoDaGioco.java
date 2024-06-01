package Giocatori;

import java.util.Set;

import Carte.*;
import Eccezioni.*;

public class CampoDaGioco {
	private Carta campoPersonale[][];
	private final int nRigheTabella=50;
	private final int nColonneTabella=50;
	
	/***
	 * costruttore del campo da gioco dell'utente
	 * @param cartaStarter che viene assegnata all'utente all'inizio della partita
	 */
	public CampoDaGioco(Carta cartaStarter) {
		//generazione della matrice di gioco
		this.campoPersonale = new Carta[this.nRigheTabella][this.nColonneTabella];
		
		for(int i=0; i<this.nRigheTabella; i++) {
			for(int j=0; j<this.nColonneTabella; j++) {
				this.campoPersonale[i][j] = null;
			}
		}
		
		this.campoPersonale[(this.nRigheTabella/2)-1][(this.nColonneTabella/2)-1]=cartaStarter;
	}
	
	//la carta starter é nella posizione 24x24
	
	/***
	 * funzione per posizionare la carta sulla matrice
	 * @param carta: che vuoi posizionare
	 * @param nRiga: num di riga su cui é posizionata la carta che vuoi sovrappore (da 0 a 49)
	 * @param nColonna:  num di colonna su cui é posizionata la carta che vuoi sovrapporre (da 0 a 49)
	 * @param nAngolo: num dell'angolo su cui vuoi posizionare la carta (da 0 a 7 in senso orario fronte e retro)
	 * @return ritorna false se la carta non puo' essere posizionata, true se viene posizionata
	 */
	public boolean controlloPosizionaCarta(Carta carta, int nRiga, int nColonna, int nAngolo) {
		if(campoPersonale[nRiga][nColonna]!=null && (nAngolo>=0 && nAngolo<8) && campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo)!=null) {
			if(campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).getOccupato()==false && controllaCarta(carta)==true) {
				switch (nAngolo) {
				case 0,4:
					if(campoPersonale[nRiga-1][nColonna-1] == null) {
						campoPersonale[nRiga-1][nColonna-1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
				case 1,5:
					if(campoPersonale[nRiga-1][nColonna+1] == null) {
						campoPersonale[nRiga-1][nColonna+1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
				case 2,6:
					if(campoPersonale[nRiga+1][nColonna+1] == null) {
						campoPersonale[nRiga+1][nColonna+1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
				case 3,7:
					if(campoPersonale[nRiga+1][nColonna-1] == null) {
						campoPersonale[nRiga+1][nColonna-1] = carta;
						campoPersonale[nRiga][nColonna].getSpecifiAngolo(nAngolo).setOccupato(true);
						return true;
					}
					break;
						
				}
				
			}
			
		}
		return false;
	}
	
	/***
	 * controlla se la carta è di tipo risorsa o oro; nel secondo caso richiama la funzione controllaCarta(CartaOro carta)
	 * @param carta: carta che si vuole posizionare
	 * @return ritorna true se la carta è posizionabile, false in caso contrario
	 */
	public boolean controllaCarta(Carta carta) {
		if(carta instanceof CartaRisorsa) {
			return true;
		}
		else if(carta instanceof CartaOro) {
			return controllaCarta((CartaOro) carta);
		}
		
		return false;
	}
	
	/***
	 * controlla se la carta oro passata è posizionabile
	 * @param carta: carta che si vuole posizionare
	 * @return ritorna true se la carta è posizionabile, false in caso contrario
	 */
	public boolean controllaCarta(CartaOro carta) {
		
		int contaFigure[] = new int[7]; //0=lupi; 1=foglie; 2=farfalle; 3=funghi; 4=boccetta; 5=piuma; 6=pergamena
		int contaRichieste[] = new int [4]; //0=lupi; 1=foglie; 2=farfalle; 3=funghi
		boolean posiziona = false;
		
		if(carta.getFronte() == false) {
			//la carta e' giocata di retro. non bisogna fare controlli
			posiziona = true;
			return posiziona;
		}
		else {
			for(int i=0; i<contaFigure.length; i++)
				contaFigure[i]=0;
			for(int i=0; i<contaRichieste.length; i++)
				contaRichieste[i]=0;
			
			contaFigure(contaFigure);
			
			for(Disegno dis : carta.getDisegnoRichieste()) {
				if(dis!=null) {
					contatoreDiFigure(contaRichieste, dis);
				}
			}
			
			posiziona=true;
			for(int i=0; i<contaRichieste.length; i++) {
				if(contaRichieste[i]<=contaFigure[i] && posiziona == true)
					posiziona=true;
				else
					posiziona=false;
			}
			
			if(posiziona) {
				return true;
			}
			else {
				System.out.print("Per posizionare la carta ti servono un totale di: ");
				if(contaRichieste[0]!=0)
					System.out.print(contaRichieste[0] + " lupi ");
				if(contaRichieste[1]!=0)
					System.out.print(contaRichieste[1] + " foglie ");
				if(contaRichieste[2]!=0)
					System.out.print(contaRichieste[2] + " farfalle ");
				if(contaRichieste[3]!=0)
					System.out.print(contaRichieste[3] + " funghi ");
				
				System.out.println();
				return false;
			}
		}
	}
	
	
	/**
	 * conta tutte le figure presenti sul campo da gioco del giocatore
	 * @param contaFigure[]: vettore di interi i cui elementi verrano usati come contatori da incrementare
	 */
	public void contaFigure(int contaFigure[]) {
		for(int i=0; i<this.nRigheTabella; i++) {
			for(int j=0; j<this.nColonneTabella; j++) { //scorrimento della matrice
				if(this.campoPersonale[i][j] != null) { //se troviamo una carta..
					if(campoPersonale[i][j].getFronte()==true) { //se è giocata di fronte..
						for(int k=0; k<4; k++) {
							if(campoPersonale[i][j].getSpecifiAngolo(k)!=null && campoPersonale[i][j].getSpecifiAngolo(k).getOccupato()==false) { //se l'angolo non è occupato e quindi il disegno si può vedere..
								contatoreDiFigure(contaFigure, campoPersonale[i][j].getSpecifiAngolo(k).getDisegno()); //incremento contatore nel vettore corrispondente al disegno tramite una funzione apposita
							}
						}
					}
					else { 
						if(campoPersonale[i][j].getColore().equals(Colore.giallo)) {
							for(int k=4; k<8; k++) {
								if(campoPersonale[i][j].getSpecifiAngolo(k)!=null && campoPersonale[i][j].getSpecifiAngolo(k).getOccupato()==false) { //se l'angolo non è occupato e quindi il disegno si può vedere..
									contatoreDiFigure(contaFigure, campoPersonale[i][j].getSpecifiAngolo(k).getDisegno()); //incremento contatore nel vettore corrispondente al disegno tramite una funzione apposita
								}
							}
						}
						//invece se è giocata con il retro a vista si controlla il disegno al centro della carta
						for(Disegno dis : campoPersonale[i][j].getDisegni()) { //essendoci al max 3 disegni, scorro e controllo se esiste il disegno
							if(dis!=null) {
								contatoreDiFigure(contaFigure, dis);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * contatore che incrementa l'elemento del vettore corrispondente al disegno passato in chiamata
	 * @param contatore[] vettore che contiene i contatori da incrementare
	 * @param dis: disegno che verrà sottoposto a switch
	 */
	public void contatoreDiFigure(int contatore[], Disegno dis) {
		if(dis != null) {
			switch(dis) {
			case lupo:
				contatore[0]++;
				break;
			case foglia:
				contatore[1]++;
				break;
			case farfalla:
				contatore[2]++;
				break;
			case fungo:
				contatore[3]++;
				break;
			case boccetta:
				contatore[4]++;
				break;
			case piuma:
				contatore[5]++;
				break;
			case pergamena:
				contatore[6]++;
				break;
			default: //case null
				break;
			}
		}
	}
	
	/***
	 * funzione per contare gli angoli sovrapposti da una carta oro per il conteggio dei punti da assegnare all'utente
	 * @param carta la carta oro da cui partire per contare gli angoli sovrapposti
	 * @return il numero degli angoli che la carta sovrappone
	 */
	public int angoliSovrapposti(Carta carta) {
	    int count = 0; // conta gli angoli sovrapposti
	    int[] index;  // 0: riga, 1: colonna

	    // coordinate della carta da controllare
	    try {
	        index = trovaCoordinateCarta(carta);

	        // Controllo angolo in alto a sinistra
	        if (carta.getSpecifiAngolo(0) != null) {
	            Carta cartaAdj = trovaCarta(index[0] - 1, index[1] - 1);
	            if (cartaAdj != null && cartaAdj.getSpecifiAngolo(2) != null) {
	                count++;
	            }
	        }

	        // Controllo angolo in alto a destra
	        if (carta.getSpecifiAngolo(1) != null) {
	            Carta cartaAdj = trovaCarta(index[0] - 1, index[1] + 1);
	            if (cartaAdj != null && cartaAdj.getSpecifiAngolo(3) != null) {
	                count++;
	            }
	        }

	        // Controllo angolo in basso a destra
	        if (carta.getSpecifiAngolo(2) != null) {
	            Carta cartaAdj = trovaCarta(index[0] + 1, index[1] + 1);
	            if (cartaAdj != null && cartaAdj.getSpecifiAngolo(0) != null) {
	                count++;
	            }
	        }

	        // Controllo angolo in basso a sinistra
	        if (carta.getSpecifiAngolo(3) != null) {
	            Carta cartaAdj = trovaCarta(index[0] + 1, index[1] - 1);
	            if (cartaAdj != null && cartaAdj.getSpecifiAngolo(1) != null) {
	                count++;
	            }
	        }

	        // finito il conteggio degli angoli sovrapposti
	        return count;
	    } catch (ElementNotFoundException e) {
	        // la carta non è stata trovata nella matrice, lancia l'eccezione
	        e.printStackTrace();
	        throw e;
	    }
	}

	
	/***
	 * trova le coordinate di una specifica carta nella matrice
	 * @param carta: la carta di cui si vogliono cercare le coordinate
	 * @return un vettore contenente i due indici della matrice in cui si trova la carta
	 */
	public int[] trovaCoordinateCarta (Carta carta){
		int index [] = new int [2];
		
		if(carta == null) {
			//é stato passato un elemento nullo come parametro
			throw new IllegalArgumentException("I parametri non possono essere nulli");
		}
		//ciclo per scorrere gli elementi della matrice
		for (int i=0; i<this.nRigheTabella; i++) {
			for (int j=0; j<this.nColonneTabella; j++) {
				if(campoPersonale[i][j]!=null) {
					if(campoPersonale[i][j].equals(carta)) {
						index[0] = i;
						index[1] = j;
						return index;
					}/*
					else {
						throw new ElementNotFoundException("L'elemento " + carta + " non è stato trovato nella matrice.");
					}*/
				}
			}
		}
			
		return null;
	}
	
	/***
	 * cerca una carta nella matrice in base alle sue coordinate
	 * @param riga: la riga della carta nella matrice
	 * @param colonna: la colonna della carta nella matrice
	 * @return la carta se e' stata trovata, ElementNotFoundException se la carta non c'é
	 */
	public Carta trovaCarta(int riga, int colonna) {
	    if (riga < 0 || riga >= this.nRigheTabella || colonna < 0 || colonna >= this.nColonneTabella) {
	        throw new IndexOutOfBoundsException("Le coordinate specificate sono fuori dai limiti della matrice.");
	    }
	    return campoPersonale[riga][colonna];
	}
	
	/***
	 * passato un set di interi contenenti degli indici, controlla che dati altri indici non siano già presenti nel set.
	 * Utilizzata nei controlli delle carte obiettivo
	 * @param index il set contenente gli indici
	 * @param a: primo indice
	 * @param b: secondo indice
	 * @param c: terzo indice
	 * @param d: quarto indice
	 * @param e: quinto indice
	 * @param f: sesto indice
	 * @return true se il set non li contiene, false se li contiene
	 */
	public boolean controllaIndici(Set<Integer> index, int a, int b, int c, int d, int e, int f) {
		if(!index.contains(a) && !index.contains(b) && !index.contains(c) && !index.contains(d) && !index.contains(e) && !index.contains(f))
			return true;
		return false;
	}
	
	/**
	 * @deprecated
	 * stampa della matrice ossia l'area di gioco del giocatore
	 */
	public void stampaCampoDaGioco() {
		for(int i=0; i<nRigheTabella; i++) {
			for(int j=0; j<nColonneTabella; j++) {
				if(campoPersonale[i][j]!=null) {
					System.out.println("Riga:"+i+ " Col:"+j);
					System.out.println(campoPersonale[i][j].stampa());
				}
			}
			System.out.println();
		}
		this.stampa();
	}
	
	public void stampa() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n");
		
		for(int i = 0; i < nRigheTabella; i++) { //righe
			if(this.isRowEmpty(campoPersonale, i) == false) {
				//prima riga: indicizzazione di ogni cella
				for(int j = 0; j < nColonneTabella; j++) {
					if(this.isColoumnEmpty(campoPersonale, j) == false) {
						sb.append(formatToFixedWidth(i + "x" + j, 21));
					}		
				}
				sb.append("\n");
					
				//seconda riga: angoli in alto
				for(int j = 0; j < nColonneTabella; j++) {
					if(this.isColoumnEmpty(campoPersonale, j) == false) {
						//c'e' qualcosa nella riga
						if(campoPersonale[i][j] != null) {
							if(campoPersonale[i][j].getFronte() == true) {
								sb.append("|");
								sb.append(campoPersonale[i][j].getAngoli()[0] != null ? formatToFixedWidth(campoPersonale[i][j].getAngoli()[0].getDisegno() + "  ", 10) 
										: formatToFixedWidth(" ", 10));
								sb.append(campoPersonale[i][j].getAngoli()[1] != null ? formatToFixedWidth(campoPersonale[i][j].getAngoli()[1].getDisegno() + "  ", 10)  
										: formatToFixedWidth(" ", 10));
								sb.append("|");
							}
							else {
								//giocata di retro: solo la carta starter ha degli angoli con delle figure sul retro
								if(campoPersonale[i][j].getColore().equals(Colore.giallo)) {
									//carta starter
									sb.append("|");
									sb.append(campoPersonale[i][j].getAngoli()[4] != null ? formatToFixedWidth(campoPersonale[i][j].getAngoli()[4].getDisegno() + "  ", 10) 
											: formatToFixedWidth(" ", 10));
									sb.append(campoPersonale[i][j].getAngoli()[5] != null ? formatToFixedWidth(campoPersonale[i][j].getAngoli()[5].getDisegno() + "  ", 10) 
											: formatToFixedWidth(" ", 10));
									sb.append("|");
								}
								else {
									//carta normale
									sb.append("|                   |");
								}
							}
						}
						else {
							sb.append("|                   |");
						}
					}
				}
				sb.append("\n");
				
				//terza riga: disegni in centro alla carta
				for(int j = 0; j < nColonneTabella; j++) {
					if(this.isColoumnEmpty(campoPersonale, j) == false) {
						if(campoPersonale[i][j] != null) {
							if(campoPersonale[i][j].getFronte() == true) {
								//carte giocate di fronte non hanno disegni centrali
								sb.append("|                    |");
							}
							else {
								//carte giocate di retro
								sb.append("|");
								sb.append(campoPersonale[i][j].getDisegni()[0] != null ? formatToFixedWidth(campoPersonale[i][j].getDisegni()[0] + "  ", 6) 
										: formatToFixedWidth(" ", 6));
								sb.append(campoPersonale[i][j].getDisegni()[1] != null ? formatToFixedWidth(campoPersonale[i][j].getDisegni()[1] + "  ", 7) 
										: formatToFixedWidth(" ", 7));
								sb.append(campoPersonale[i][j].getDisegni()[2] != null ? formatToFixedWidth(campoPersonale[i][j].getDisegni()[2] + "  ", 6) 
										: formatToFixedWidth(" ", 6));
								sb.append("|");
							}
						}
						else {
							sb.append("|                   |");
						}
					}
				}
				sb.append("\n");
				
				//quarta riga: angoli in basso
				for(int j = 0; j < nColonneTabella; j++) {
					if(this.isColoumnEmpty(campoPersonale, j) == false) {
						if(campoPersonale[i][j] != null) {
							if(campoPersonale[i][j].getFronte() == true) {
								sb.append("|");
								sb.append(campoPersonale[i][j].getAngoli()[3] != null ? formatToFixedWidth(campoPersonale[i][j].getAngoli()[3].getDisegno() + "  ", 10) 
										: formatToFixedWidth(" ", 10));
								sb.append(campoPersonale[i][j].getAngoli()[2] != null ? formatToFixedWidth(campoPersonale[i][j].getAngoli()[2].getDisegno() + "  ", 10) 
										: formatToFixedWidth(" ", 10));
								sb.append("|");
							}
							else {
								//carte giocate di retro
								if(campoPersonale[i][j].getColore().equals(Colore.giallo)) {
									//carta starter
									sb.append("|");
									sb.append(campoPersonale[i][j].getAngoli()[7] != null ? formatToFixedWidth(campoPersonale[i][j].getAngoli()[7].getDisegno() + "  ", 10) 
											: formatToFixedWidth(" ", 10));
									sb.append(campoPersonale[i][j].getAngoli()[6] != null ? formatToFixedWidth(campoPersonale[i][j].getAngoli()[6].getDisegno() + "  ", 10) 
											: formatToFixedWidth(" ", 10));
									sb.append("|");
								}
								else {
									//carta normale
									sb.append("|                   |");
								}
							}
						}
						else {
							sb.append("|                   |");
						}
					}
				}
				sb.append("\n");
				
				//quarta riga: colore della carta
				for(int j = 0; j < nColonneTabella; j++) {
					if(this.isColoumnEmpty(campoPersonale, j) == false) {
						if(campoPersonale[i][j] != null) {
							sb.append("|");
							sb.append(formatToFixedWidth("colore: " + campoPersonale[i][j].getColore() , 20));
							sb.append("|");
						}
						else {
							sb.append("|                   |");
						}
					}
				}
				sb.append("\n\n\n");
			}
			
		}
		System.out.println(sb.toString());
	}
	
	private String formatToFixedWidth(String str, int width) {
        if (str.length() > width) {
            return str.substring(0, width);
        } else {
            return String.format("%-" + width + "s", str);
        }
    }
	
	private boolean isRowEmpty(Carta [][] matrix, int row) {
        for (int i = 0; i < matrix[row].length; i++) {
            if (matrix[row][i] != null) {
                return false;
            }
        }
        return true;
    }
	
	private boolean isColoumnEmpty(Carta [][] matrix, int coloumn) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][coloumn] != null) {
                return false;
            }
        }
        return true;
    }

	public int getnRigheTabella() {
		return nRigheTabella;
	}

	public int getnColonneTabella() {
		return nColonneTabella;
	}

	public Carta[][] getCampoPersonale() {
		return campoPersonale;
	}
}