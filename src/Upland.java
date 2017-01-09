import java.util.Vector;

public class Upland {

	private int width; //eixo x
	
	private int height; // eixo y
	
	private int [][]ground; //eixo y cresce acompanhando as linhas
							//eixo x cresce acompanhando a coluna
	
	private Vector<Operator> operators = new Vector<Operator>();
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int[][] getGround(){
		return ground;
	}
	
	//para a entrada especificada no problema é necessário somar 1 as dimensões
	//antes de usar esse construtor
	public Upland(int width, int height){
		this.width = width;
		this.height = height;
		ground = new int[width][height];
		startGround();
	}
	
	public Upland(){
		this(2,2);
	}
	
	//inicializa o terreno sem nenhuma sonda
	public void startGround(){
		for(int i = 0; i < height; i++){
			for(int j = 0 ; j < width; j++){
				ground[i][j] = 0;
			}
		}
	}
	
	//coloca ou tira sondas do terreno dependendo do valor de value
	public void updateGround(int x, int y, int value){
		ground[x][y] = value;
	}
	//primeiro vamos assumir que não tentem ser colocadas sondas em lugares repetidos
	//vamos colocar uma sonda no ground
	public void addOperator(Operator operator){
		this.operators.add(operator);
		int posX = operator.getX();
		int posY = operator.getY();
		ground[posX][posY] = 1;
		
	}
	
	public Vector<Operator> getOperators(){
		return operators;
	}

	public void processOperatorComands(){
		for(int i = 0; i < operators.size(); i++){
			operators.elementAt(i).processComands();
		}
	}
	
	public void printStates(){
		for(int i = 0; i < operators.size(); i++){
			System.out.println(operators.elementAt(i).getState());
		}
	}
	
}
