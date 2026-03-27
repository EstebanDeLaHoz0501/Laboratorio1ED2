package Core.Arbol;

import Core.CursoManager;
import Core.Nodo;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;


/**
 *
 * @author luna

    
 */
public class OperacionesArbol {
    //METODOS POR ID
    public static Nodo insertar1(AVL tree, int id){
        return new Nodo(CursoManager.getInstance().getCurso(id));
    }
    
    public static Nodo insertar(Nodo raiz, int id, AVL tree){
        Nodo root = insertarAux(raiz, id, tree);
        System.out.println("paso x aqui");
        return root;
    }
    
    public static Nodo insertarAux(Nodo raiz, int id, AVL tree){
        double metrica = CursoManager.getSatisfaction(id);
        if(raiz== null){
            return new Nodo(CursoManager.getInstance().getCurso(id));
        }
        if(raiz.getMetrica() < metrica){
            raiz.setRight(insertarAux(raiz.getRight(), id, tree));
            //raiz.right = insertarAux(raiz.right, id);
        }
        if(raiz.getMetrica() > metrica){
            raiz.setLeft(insertarAux(raiz.getLeft(), id, tree));
            //raiz.left = insertarAux(raiz.left, id);
        }
        return tree.balancear(raiz);
    }
    
    public static ArrayList<Nodo> search(Nodo root, int id){
        System.out.println("a search entró "+root);
        double metrica = CursoManager.getInstance().getCurso(id).getSatisfaction();
        return searchAux(metrica, root, null, null);
    }
    
    public static ArrayList<Nodo> searchAux(double metrica, Nodo p, Nodo pad, Nodo abue){
        System.out.println("a search aux entró "+p);
        System.out.println("la metrica a buscar es: "+metrica+" Y LA DE P ES "+p.getMetrica());
        ArrayList<Nodo> lista = new ArrayList<>();
        if(p==null){
            System.out.println("p es null");
            lista.add(null);               
            lista.add(pad);
            lista.add(abue);
            lista.add(null);
            return lista;
        }
        if(p.getMetrica()==metrica){
            System.out.println("p es "+p);
            lista.add(p);
            lista.add(pad);
            lista.add(abue);
            Nodo tio = null;
            if(abue != null){
                System.out.println("abue no es null");
                if(abue.getLeft()==pad){
                    tio = abue.getRight();
                }else{
                    tio = abue.getLeft();
                }
            }
            lista.add(tio);
            System.out.println("retorname");
            return lista;
        }
        System.out.println("queeee");
        if(p.getMetrica() >metrica){
            return searchAux(metrica, p.getLeft(), p, pad);
        }else{
            return searchAux(metrica, p.getRight(), p, pad);
        }
    }
    
public static Nodo delete(Nodo root, int id, AVL tree){
    ArrayList<Nodo> res = search(root, id);
    Nodo p = res.get(0);
    Nodo pad = res.get(1);

    if(p != null){
        if(pad == null){
            if(p.getLeft() == null && p.getRight() == null){
                return null;
            }
            else if(p.getLeft() == null){
                return p.getRight();
            }
            else if(p.getRight() == null){
                return p.getLeft();
            }
            else{
                ArrayList<Nodo> s = sus(p);
                Nodo psus = s.get(0);
                Nodo padsus = s.get(1);
                Nodo phijo = s.get(2);

                p.setCurso(psus.getCurso());

                if(padsus == p){
                    padsus.setRight(psus.getRight());
                }else{
                    if(phijo == null){
                        padsus.setLeft(null);
                    }else{
                        padsus.setLeft(phijo);
                    }
                }

                return tree.balancear(root);
            }
        }

        if(p.getLeft() == null && p.getRight() == null){
            if(pad.getLeft() == p){
                pad.setLeft(null);
            }else{
                pad.setRight(null);
            }

        }else if(p.getLeft() == null && p.getRight() != null){
            if(pad.getLeft() == p){
                pad.setLeft(p.getRight());
            }else{
                pad.setRight(p.getRight());
            }

        }else if(p.getLeft() != null && p.getRight() == null){
            if(pad.getLeft() == p){
                pad.setLeft(p.getLeft());
            }else{
                pad.setRight(p.getLeft());
            }

        }else{
            ArrayList<Nodo> s = sus(p);
            Nodo psus = s.get(0);
            Nodo padsus = s.get(1);
            Nodo phijo = s.get(2);

            p.setCurso(psus.getCurso());

            if(padsus == p){
                padsus.setRight(psus.getRight());
            }else{
                if(phijo == null){
                    padsus.setLeft(null);
                }else{
                    padsus.setLeft(phijo);
                }
            }
        }
   
        return tree.balancear(root);
    }

    return tree.balancear(root);
}
    
    //que es sus? preguntale a esteban
    public static ArrayList sus(Nodo node){
        ArrayList<Nodo> lista = new ArrayList<>();
        Nodo p = node.getRight();
        Nodo pad = node;
        while(p.getLeft()!=null){
            p = p.getLeft();
            pad = p;
        }
        lista.add(p);
        lista.add(pad);
        lista.add(p.getRight());
        return lista;
    }
    
    //METODOS POR METRICA
    public static ArrayList<Nodo> search(Nodo root, double metrica){
        return searchAux(metrica, root, null, null);
    }
    
    //solucionar
    public static Nodo delete(Nodo root, Double metrica, AVL tree){
        ArrayList<Nodo> res = search(root, metrica);
        Nodo p = res.get(0);
        Nodo pad = res.get(1);

        if(p != null){
            if(pad == null){
                if(p.getLeft() == null && p.getRight() == null){
                    return null;
                }
                else if(p.getLeft() == null){
                    return p.getRight();
                }
                else if(p.getRight() == null){
                    return p.getLeft();
                }
                else{
                    ArrayList<Nodo> s = sus(p);
                    Nodo psus = s.get(0);
                    Nodo padsus = s.get(1);
                    Nodo phijo = s.get(2);

                    p.setCurso(psus.getCurso());

                    if(padsus == p){
                        padsus.setRight(psus.getRight());
                    }else{
                        if(phijo == null){
                            padsus.setLeft(null);
                        }else{
                            padsus.setLeft(phijo);
                        }
                    }

                    return tree.balancear(root);
                }
            }

            if(p.getLeft() == null && p.getRight() == null){
                if(pad.getLeft() == p){
                    pad.setLeft(null);
                }else{
                    pad.setRight(null);
                }

            }else if(p.getLeft() == null && p.getRight() != null){
                if(pad.getLeft() == p){
                    pad.setLeft(p.getRight());
                }else{
                    pad.setRight(p.getRight());
                }

            }else if(p.getLeft() != null && p.getRight() == null){
                if(pad.getLeft() == p){
                    pad.setLeft(p.getLeft());
                }else{
                    pad.setRight(p.getLeft());
                }

            }else{
                ArrayList<Nodo> s = sus(p);
                Nodo psus = s.get(0);
                Nodo padsus = s.get(1);
                Nodo phijo = s.get(2);

                p.setCurso(psus.getCurso());

                if(padsus == p){
                    padsus.setRight(psus.getRight());
                }else{
                    if(phijo == null){
                        padsus.setLeft(null);
                    }else{
                        padsus.setLeft(phijo);
                    }
                }
            }

            return tree.balancear(root);
        }

        return tree.balancear(root);
    }
    
    
    //PUNTO 4:
    
    //Llena la lista de nodos
    public static void getAfterDateAux(Nodo root, Instant date, ArrayList<String> nodos){
        if(root == null) return;
        
        if(root.getCurso().getCreated().isBefore(date)){
            return;
        } else{
            nodos.add(Integer.toString(root.getId()));
        }
        
        getAfterDateAux(root.getLeft(), date, nodos);
        getAfterDateAux(root.getRight(), date, nodos);
        
        
    }
    
    //retorna la lista de nodos
    public static ArrayList<String> getAfterDate(Nodo root, Instant date){
        ArrayList<String> afterDate = new ArrayList<>();
        getAfterDateAux(root, date, afterDate);
        
        return afterDate;
    }
    
    //Llena la lista de nodos
    public static void classesInRangeAux(Nodo root, int min, int max, ArrayList<String> nodos){
        if (root == null) return;
        
        //para abreviar el if
        int numLectures = root.getCurso().getNum_published_lectures();
        
        if(numLectures <= min || numLectures >= max){
            return;
        } else{
            nodos.add(Integer.toString(root.getId()));
        }
        
        classesInRangeAux(root.getLeft(), min, max, nodos);
        classesInRangeAux(root.getRight(), min, max, nodos);
        
    }
    
    //retorna la lista de nodos
    public static ArrayList<String> classesInRange(Nodo root, int min, int max){
        ArrayList<String> nodos = new ArrayList<>();
        classesInRangeAux(root, min, max, nodos);
        
        return nodos;
  
    }
    
    //llena la lista de nodos
    public static void highlyGradedAux(Nodo root, ArrayList<String> nodos){
        if(root == null) return;
        
        if(root.getCurso().getPositive_reviews() <= root.getCurso().getNegative_reviews() + root.getCurso().getNeutral_reviews()){
            return;
        } else{
            nodos.add(Integer.toString(root.getId()));
        }
        
        highlyGradedAux(root.getLeft(), nodos);
        highlyGradedAux(root.getRight(), nodos);
    }
    // retorna la lista de nodos
    public static ArrayList<String> highlyGraded(Nodo root){
        ArrayList<String> nodos = new ArrayList<>();
        highlyGradedAux(root, nodos);
        
        return nodos;
        
    }
    
    //para encontrar el promedio de reseñas
    public static double sumTodasReseñas(Nodo root){
        if(root == null) return 0;
        int revNumber = root.getCurso().getNum_reviews();
        
        return revNumber + sumTodasReseñas(root.getLeft()) + sumTodasReseñas(root.getRight());
        
    }
    
    //para encontrar el promedio de reseñas
    public static double numNodos(Nodo root){
        if (root == null) return 0;
        
        return 1 + numNodos(root.getLeft()) + numNodos(root.getRight());
    }
    
   public static double promedioRatings(AVL arbol){
       return sumTodasReseñas(arbol.getRaiz())/numNodos(arbol.getRaiz()); 
   }
   
   public static void posHigherThanAvgAux(Nodo root, AVL arbol, ArrayList<String> nodos){
        if(root == null) return;
        double avg = promedioRatings(arbol);
        System.out.println("soy avg"+avg);
        System.out.println("soy positive" + root.getCurso().getPositive_reviews());
        if(root.getCurso().getPositive_reviews() <= avg){
            return;
        }else{
            nodos.add(Integer.toString(root.getId()));
        }
        
        posHigherThanAvgAux(root.getLeft(), arbol, nodos);
        posHigherThanAvgAux(root.getRight(), arbol, nodos);
    }
   
   public static void negHigherThanAvgAux(Nodo root, AVL arbol, ArrayList<String> nodos){
       if(root == null) return;
       double avg = promedioRatings(arbol);
        
       if(root.getCurso().getNegative_reviews() <= avg){
           return;
       }else{
         nodos.add(Integer.toString(root.getId()));
        }
        
        negHigherThanAvgAux(root.getLeft(), arbol, nodos);
        negHigherThanAvgAux(root.getRight(), arbol, nodos);
    }
    
   public static void neutHigherThanAvgAux(Nodo root, AVL arbol, ArrayList<String> nodos){
        if(root == null) return;
        double avg = promedioRatings(arbol);
        
        if(root.getCurso().getNeutral_reviews() <= avg){
            return;
        }else{
            nodos.add(Integer.toString(root.getId()));
        }
        
        neutHigherThanAvgAux(root.getLeft(), arbol, nodos);
        neutHigherThanAvgAux(root.getRight(), arbol, nodos);
    } 
   
   public static ArrayList<String> posHigherThanAvg(Nodo root, AVL arbol){
       ArrayList<String> nodos = new ArrayList<>();
       posHigherThanAvgAux(root, arbol, nodos);
       
       return nodos;
       
   }
   
   public static ArrayList<String> negHigherThanAvg(Nodo root, AVL arbol){
       ArrayList<String> nodos = new ArrayList<>();
       negHigherThanAvgAux(root, arbol, nodos);
       
       return nodos;
       
   }
   
   public static ArrayList<String> neutHigherThanAvg(Nodo root, AVL arbol){
       ArrayList<String> nodos = new ArrayList<>();
       neutHigherThanAvgAux(root, arbol, nodos);
       
       return nodos;
       
   }
   
    public static void Preorden (Nodo ndo){ 
        if(ndo != null){ 
            System.out.print(ndo.getId() + ", "); 
            Preorden(ndo.getLeft());
            
            Preorden(ndo.getRight());
        } 
    }
}
