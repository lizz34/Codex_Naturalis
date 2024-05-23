CODEX 
Videogioco online tratto dal gioco da tavolo Codex di Cranio Creations.

## indice
- [istruzioni per giocare](#istruzione)
- [funzionalità principali del gioco](#funzionalità)
- [esempi di utilizzo](#esempi)
- [stato del progetto](#stato)
- [licenza](#licenza)

## istruzione

Per far partire il gioco bisogna scaricare la repository in locale e aprirla su Eclipse; poi far partire il file Main.java contenuto nel package Main.

## funzionalità
funzione per posizionare una carta sul campo da gioco:
posiziona una carta dalla mano del giocatore nel suo campo da gioco, se l'azione va a buon fine la carta viene anche cancellata dalla sua mano e 
se la carta é giocata sul retro non viene fatto nessun controllo sul punteggio,
nel caso in cui la carta sia giocata di fronte viene incrementato il punteggio del giocatore con appositi controlli.
Lancia l'eccezione ElementNotFound nel caso in cui la carta passata non esista nella matrice.

```java
	public boolean posizionaCarta(int nCarta, int nRiga, int nColonna, int nAngolo, boolean fronte) {
		Carta cartaScelta = mano.get(nCarta);
		
		if (campoPersonale.controlloPosizionaCarta(cartaScelta, nRiga, nColonna, nAngolo) == false) {
			return false;
		}
		else {
			if(fronte == true) {
				if(cartaScelta instanceof CartaRisorsa) {
					this.punteggio += ((CartaRisorsa) cartaScelta).getPunti();
				}
				else if(cartaScelta instanceof CartaOro) {
					if(((CartaOro) cartaScelta).getCriterioPunti() == null) {
						this.punteggio += ((CartaOro) cartaScelta).getPunti();
					}
					else {
						Disegno criterio = ((CartaOro) cartaScelta).getCriterioPunti();
						int puntiCarta = ((CartaOro) cartaScelta).getPunti();
						//0=lupi; 1=foglie; 2=farfalle; 3=funghi; 4=boccetta; 5=piuma; 6=pergamena
						int contaDisegni [] = new int [7];
						campoPersonale.contaFigure(contaDisegni);
						
						switch(criterio) {
							case angoloSovrapposto:
								
								try {
									int countAngoli = 0;
									countAngoli = campoPersonale.angoliSovrapposti(cartaScelta);
									this.punteggio += (puntiCarta * countAngoli);
								}
								catch(ElementNotFoundException e) {
									return false;
								}
							break;
							case boccetta:
								this.punteggio += (puntiCarta * contaDisegni[4]);
							break;
							case piuma:
								this.punteggio += (puntiCarta * contaDisegni[5]);
							break;
							case pergamena:
								this.punteggio += (puntiCarta * contaDisegni[6]);
							break;
							default:
							break;
						}
					}
				}
			}
			mano.remove(nCarta);
			return true;
		}		
	}
```

funzione per controllare il posizionamento di una carta nel campo da gioco: 
per organizzare il campo da gioco si è usata una matrice 50x50, nella quale la carta starter viene messa in posizione 24x24.
Ogni qualvolta si desidera posizionare una carta bisogna inserire le coordinate (riga, colonna e numero angolo) della carta già presente nella matrice a cui si vuole attaccare quella che si ha in mano.
La funzione controlla per prima cosa che esista una carta nelle coordinate selezionate; se esiste una carta controlla se l'angolo scelto è libero o occupato.
Nel caso in cui sia possibile posizionare la carta, guarda il tipo della carta: se è carta risorsa la posiziona, se è carta oro controlla che i criteri di posizionamento siano soddisfatti.
Le coordinate della carta da posizionare vengono calcolate automaticamente in base all'angolo scelto, esempio: se si è scelto l'angolo in alto a sinistra, le coordinate saranno: numRiga - 1 e numColonna - 1;
inoltre controlla che in quella data posizione non ci sia già presente un'altra carta.
```java
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
```
funzione per il controllo della carta obiettivo:
Controlla le due carte obiettivo del tipo: due carte in verticale, una sopra l'altra, con un dato colore e un'altra carta posta in diagonale rispetto a quella più alta con un altro colore.
Scorre tutta la matrice in cerca di una carta con lo stesso colore di quella in diagonale nella carta obiettivo; quando la trova controlla se ci sono due carte con il giusto colore disposte verticalmente
in una colonna diagonale rispetto alla prima carta considerata.
Il set di interi serve per salvare le coordinate delle carte trovate in modo da evitare di conteggiare più volte le stesse carte per uno stesso obiettivo.
esempio: due carte verticali in basso a destra rispetto alla prima carta; la seconda carta cercata avrà coordinate r+1 e c+1 e la terza r+2 e c+1.


```java
	public int obiettivoVerticaleInferiore(Colore colVerticale, Colore col1, int modColonna, int modRiga1, int modRiga2) {
		Set<Integer> index = new HashSet<Integer>();
		int tris=0;
		
		for(int i = 0; i < nRigheTabella; i++) {
			for(int j = 0; j < nColonneTabella; j++) {
				if(campoPersonale[i][j] != null) {
					if(campoPersonale[i][j].getColore().equals(col1)) {
						if(campoPersonale[i+modRiga1][j+modColonna]!=null && campoPersonale[i+modRiga2][j+modColonna]!=null) {
							if(campoPersonale[i+modRiga1][j+modColonna].getColore().equals(colVerticale) &&
								campoPersonale[i+modRiga2][j+modColonna].getColore().equals(colVerticale)) {
								if(controllaIndici(index, i, j, i+modRiga1, j+modColonna, i+modRiga2, j+modColonna)) {
									index.add(i);
									index.add(j);
									index.add(i+modRiga1);
									index.add(j+modColonna);
									index.add(i+modRiga2);
									index.add(j+modColonna);
									tris++;
								}
							}
						}
					}
				}
			}
		}
		return tris;
	}
```

## esempi



## stato

Il gioco è giocabile tramite linea di comando o console su Eclipse.
Perchè il videogioco sia completo manca la parte grafica.

## licenza

MIT License

Copyright (c) 2024 Cranio Creation srl

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
