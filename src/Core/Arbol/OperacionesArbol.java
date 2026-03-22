package Core.Arbol;

import Core.CursoManager;
import Core.Nodo;
import java.time.Instant;
import java.util.ArrayList;/*


/**
 *
 * @author luna

    
 */
public class OperacionesArbol {
    //METODOS POR ID
    public static Nodo insertar(Nodo raiz, int id){
        Nodo root = insertarAux(raiz, id);
        AVL.balancear(raiz);
        return root;
    }
    
    public static Nodo insertarAux(Nodo raiz, int id){
        double metrica = CursoManager.getSatisfaction(id);
        if(raiz== null){
            return new Nodo(CursoManager.getCurso(id));
        }
        if(raiz.getMetrica() < metrica){
            raiz.setRight(insertarAux(raiz.getRight(), id));
            //raiz.right = insertarAux(raiz.right, id);
        }
        if(raiz.getMetrica() < metrica){
            raiz.setLeft(insertarAux(raiz.getLeft(), id));
            //raiz.left = insertarAux(raiz.left, id);
        }
        return raiz;
    }
    
    public static ArrayList<Nodo> search(Nodo root, int id){
        double metrica = CursoManager.getSatisfaction(id);
        return searchAux(metrica, root, null, null);
    }
    
    public static ArrayList<Nodo> searchAux(double metrica, Nodo p, Nodo pad, Nodo abue){
        ArrayList<Nodo> lista = new ArrayList<>();
        if(p==null){
            lista.add(null);               
            lista.add(pad);
            lista.add(abue);
            lista.add(null);
            return lista;
        }
        if(p.getMetrica()==metrica){
            lista.add(p);
            lista.add(pad);
            lista.add(abue);
            Nodo tio = null;
            if(abue != null){
                if(abue.getLeft()==pad){
                    tio = abue.getRight();
                }else{
                    tio = abue.getLeft();
                }
            }
            lista.add(tio);
            return lista;
        }
        if(p.getMetrica() >metrica){
            return searchAux(metrica, p.getLeft(), p, pad);
        }else{
            return searchAux(metrica, p.getRight(), p, pad);
        }
    }
    
    public static boolean delete(Nodo root, int id){
        Nodo p = (Nodo) search(root, id).get(0);
        Nodo pad = (Nodo) search(root, id).get(1);
        if(p!=null){
            if(p.getLeft() == null & p.getRight() == null){
                if(pad.getLeft()==p){
                    pad.setLeft(null);
                    //pad.left=null;
                }else{
                    pad.setRight(null);
                }
            }else if(p.getLeft()== null & p.getRight() != null){
                if(pad.getLeft()==p){
                    pad.setLeft(p.getRight());
                    //pad.left=p.right;
                }else{
                    
                    pad.setRight(p.getRight());
                    //pad.right=p.right;
                }
            }else if(p.getLeft()!= null & p.getRight() == null){
                if(pad.getLeft()==p){
                    pad.setLeft(p.getLeft());
                    //pad.left=p.left;
                }else{
                    pad.setRight(p.getLeft());
                    //pad.right=p.left;
                }
            }else{
                Nodo psus = (Nodo) sus(p).get(0);
                Nodo padsus = (Nodo) sus(p).get(1);
                Nodo phijo = (Nodo) sus(p).get(2);
                if(padsus == p){
                    p.setMetrica(psus.getMetrica());
                    //p.metrica = psus.metrica;
                    
                    padsus.setRight(psus.getRight());
                    //padsus.right = psus.right;
                }else{
                    if(phijo == null){
                    p.setMetrica(psus.getMetrica());
                    //p.metrica = psus.metrica;
                    
                    padsus.setLeft(null);
                    //padsus.left = null;
                    }else{
                        p.setMetrica(psus.getMetrica());
                        //p.metrica = psus.metrica;
                        
                        padsus.setLeft(phijo);
                        //padsus.left = phijo;
                    }
                }
            }
            AVL.balancear(root);
            return true;
        }
        return false;
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
        lista.add(pad);
        lista.add(p);
        lista.add(p.getRight());
        return lista;
    }
    
    //METODOS POR METRICA
    public static ArrayList<Nodo> search(Nodo root, double metrica){
        return searchAux(metrica, root, null, null);
    }
    
    public static boolean delete(Nodo root, double metrica){
        Nodo p = (Nodo) search(root, metrica).get(0);
        Nodo pad = (Nodo) search(root, metrica).get(1);
        if(p!=null){
            if(p.left == null & p.right == null){
                if(pad.left==p){
                    pad.left=null;
                }else{
                    pad.right=null;
                }
            }else if(p.left== null & p.right != null){
                if(pad.left==p){
                    pad.left=p.right;
                }else{
                    pad.right=p.right;
                }
            }else if(p.left!= null & p.right == null){
                if(pad.left==p){
                    pad.left=p.left;
                }else{
                    pad.right=p.left;
                }
            }else{
                Nodo psus = (Nodo) sus(p).get(0);
                Nodo padsus = (Nodo) sus(p).get(1);
                Nodo phijo = (Nodo) sus(p).get(2);
                if(padsus == p){
                    p.metrica = psus.metrica;
                    padsus.right = psus.right;
                }else{
                    if(phijo == null){
                    p.metrica = psus.metrica;
                    padsus.left = null;
                    }else{
                        p.metrica = psus.metrica;
                        padsus.left = phijo;
                    }
                }
            }
            AVL.balancear(root);
            return true;
        }
        return false;
    }
    
    
    //PUNTO 4:
    
    //Llena la lista de nodos
    public static void getAfterDateAux(Nodo root, Instant date, ArrayList<Nodo> nodos){
        if(root == null) return;
        
        if(root.getCurso().getCreated().isBefore(date)){
            return;
        } else{
            nodos.add(root);
        }
        
        getAfterDateAux(root.getLeft(), date, nodos);
        getAfterDateAux(root.getRight(), date, nodos);
        
        
    }
    
    //retorna la lista de nodos
    public static ArrayList<Nodo> getAfterDate(Nodo root, Instant date){
        ArrayList<Nodo> afterDate = new ArrayList<Nodo>();
        getAfterDateAux(root, date, afterDate);
        
        return afterDate;
    }
    
    //Llena la lista de nodos
    public static void classesInRangeAux(Nodo root, int min, int max, ArrayList<Nodo> nodos){
        if (root == null) return;
        
        //para abreviar el if
        int numLectures = root.getCurso().getNum_published_lectures();
        
        if(numLectures <= min || numLectures >= max){
            return;
        } else{
            nodos.add(root);
        }
        
        classesInRangeAux(root.getLeft(), min, max, nodos);
        classesInRangeAux(root.getRight(), min, max, nodos);
        
    }
    
    //retorna la lista de nodos
    public static ArrayList<Nodo> classesInRange(Nodo root, int min, int max){
        ArrayList<Nodo> nodos = new ArrayList<Nodo>();
        classesInRangeAux(root, min, max, nodos);
        
        return nodos;
  
    }
    
    //llena la lista de nodos
    public static void highlyGradedAux(Nodo root, ArrayList<Nodo> nodos){
        if(root == null) return;
        
        if(root.getCurso().getPositive_reviews() <= root.getCurso().getNegative_reviews() + root.getCurso().getNeutral_reviews()){
            return;
        } else{
            nodos.add(root);
        }
        
        highlyGradedAux(root.getLeft(), nodos);
        highlyGradedAux(root.getRight(), nodos);
    }
    // retorna la lista de nodos
    public static ArrayList<Nodo> highlyGraded(Nodo root){
        ArrayList<Nodo> nodos = new ArrayList<Nodo>();
        highlyGradedAux(root, nodos);
        
        return nodos;
        
    }
    
    //para encontrar el promedio de reseñas
    public static int sumTodasReseñas(Nodo root){
        if(root == null) return 0;
        int revNumber = root.getCurso().getNum_reviews();
        
        return revNumber + sumTodasReseñas(root.getLeft()) + sumTodasReseñas(root.getRight());
        
    }
    
    //para encontrar el promedio de reseñas
    public static int numNodos(Nodo root){
        if (root == null) return 0;
        
        return 1 + numNodos(root.getLeft()) + numNodos(root.getRight());
    }
    
    public static void posHigherThanAvg(Nodo root, ArrayList<Nodo> nodos){
        if(root == null) return;
        
        if(root.getCurso().getPositive_reviews())
    }
}
