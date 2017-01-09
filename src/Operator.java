

// classe base que possui uma sonda e a controla, as sondas só são acessiveis 
// através dessa classe
public class Operator {
	
	private Sounder sounder;
	
	private Upland upland;
	
	private String comands;

	private int actualComand = 0;
	
	//classe privada da sonda

	private Operator(){
		this.sounder = new Sounder();
	}
	
	public Operator(String comands){
		this();
		this.comands = comands;
	}
	
	private Operator(int x, int y, String dir){
		this.sounder = new Sounder(x,y,dir);
	}
	
	public Operator(int x, int y, String dir, String comands, Upland upland){
		this(x,y,dir);
		this.comands = comands;
		this.upland = upland;
	}
	
	public int getX(){
		return sounder.getX();
	}
	
	public int getY(){
		return sounder.getY();
	}
	
	public void moveSonda(){
		String comand = comands.substring(actualComand, actualComand+1);
		if(comand != null){
			switch(comand){
			case "L":
				sounder.turn(comand);
				break;
			case "R":
				sounder.turn(comand);
				break;
			case "M":

				if(!checkBorder() && !checkImpact()){
					upland.updateGround(getX(), getY(), 0);
					sounder.move();
					upland.updateGround(getX(), getY(), 1);
				}
				
				break;
				default:
					System.out.println("Invalid Comand" );
					
			}
		}
	}
	
	//check if the move will cross the border
	private boolean checkBorder(){
		int posX = getX();
		int posY = getY();
		String dir = getDirection();
		int width = upland.getWidth();
		int height = upland.getHeight();
		
		switch(dir){
		case "N":
			if(posY+1 >= height){
				return true;
			}
			break;
		case "E":
			if(posX+1>=width){
				return true;
			}
			break;
		case "S":
			if(posY-1 < 0){
				return true;
			}
			break;
		case "W":
			if(posX - 1 < 0){
				return true;
			}
			break;
			default:
				break;
		}
		return false;
	}
	
	//check if the next place is avaiable
	private boolean checkImpact(){
		int posX = getX();
		int posY = getY();
		String dir = getDirection();
		int [][] ground = upland.getGround();
		switch(dir){
		case "N":
			if(ground[posX][posY+1] == 1){
				return true;
			}
			break;
		case "E":
			if(ground[posX+1][posY] == 1){
				return true;
			}
			break;
		case "S":
			if(ground[posX][posY-1] == 1){
				return true;
			}
			break;
		case "W":
			if(ground[posX - 1][posY] == 1){
				return true;
			}
			break;
			default:
				break;
		}
		return false;
		
		
	}
	
	public String getDirection(){
		return sounder.getDirection();
	}
	
	public void processComands(){
		for(int i = 0; i < comands.length(); i++){
			actualComand = i;
			moveSonda();
		}
	}
	
	public String getState(){
		return sounder.getState();
	}
	
	private class Sounder{

		private int x;
		
		private int y;
		
		final private String directions = "NESW";
		
		
		private int currentDirection;
		
		//vira a sonda 90º a direita
		private void turnR(){
			this.currentDirection ++;
			if (this.currentDirection >= directions.length()){
				this.currentDirection = 0;
			}
		}
		
		//vira a sonda 90º a esquerda
		private void turnL(){
			this.currentDirection --;
			if(this.currentDirection < 0){
				this.currentDirection = directions.length() - 1;
			}
		}
		
		//escolhe entre turnR() ou turnL()
		public void turn(String dir){
			if(dir.equals("L")){
				turnL();
			}else if(dir.equals("R")){
				turnR();
			}else{
				System.out.println("Invalid Character");
			}
		}
		
		//atualiza a posição da sonda tendo como base para onde ela esta direcionada
		public void move(){
			String dir = this.directions.substring(currentDirection, currentDirection+1);
			switch(dir){
			case "N":
				this.y ++;
				break;
			case "E":
				this.x ++;
				break;
			case "S":
				this.y --;
				break;
			case "W":
				this.x --;
				break;
				default:
					System.out.println("Invalid Direction");
					break;
			}
		}
		
		// método para printar as informações sobre a sonda
		public String getState(){
			return this.getX() + " " + this.getY() + " " + this.directions.charAt(this.currentDirection);
		}
		
		//construtor que inicializa a sonda em um local e determina
		// pra onde ela aponta
		public Sounder(int x, int y, String dir){
			this.x = x;
			this.y = y;
			switch(dir){
			case "N":
				this.currentDirection = 0;
				break;
			case "E":
				this.currentDirection = 1;
				break;
			case "S":
				this.currentDirection = 2;
				break;
			case "W":
				this.currentDirection = 3;
				break;
				default:
					this.currentDirection = 0;
					break;
					
			}
		}
		
		public Sounder(){
			this(0,0,"N");
		}
		
		public int getX(){
			return this.x;
		}
		
		public int getY(){
			return this.y;
		}
		
		public String getDirection(){
			return this.directions.substring(currentDirection, currentDirection+1);
		}
		
	}

}
