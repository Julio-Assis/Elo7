
public class Operator {
	
	private Sounder sounder;
	
	private String comands;

	private int actualComand = 0;
	
	private class Sounder{

		private int x;
		
		private int y;
		
		final private String directions = "NESW";
		
		
		private int currentDirection;
		
		private void turnR(){
			this.currentDirection ++;
			if (this.currentDirection >= directions.length()){
				this.currentDirection = 0;
			}
		}
		
		private void turnL(){
			this.currentDirection --;
			if(this.currentDirection < 0){
				this.currentDirection = directions.length() - 1;
			}
		}
		
		public void turn(String dir){
			if(dir.equals("L")){
				turnL();
			}else if(dir.equals("R")){
				turnR();
			}else{
				System.out.println("Invalid Character");
			}
		}
		
		protected void move(){
			String dir = this.directions.substring(currentDirection, currentDirection);
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
		
		public String getState(){
			return this.getX() + " " + this.getY() + " " + this.directions.charAt(this.currentDirection);
		}
		
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
		
	}

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
	
	public Operator(int x, int y, String dir, String comands){
		this(x,y,dir);
		this.comands = comands;
	}
	
	public int getX(){
		return sounder.getX();
	}
	
	public int getY(){
		return sounder.getY();
	}
	
	public void moveSonda(){
		String comand = comands.substring(actualComand, actualComand);
		
		if(comand != null){
			switch(comand){
			case "L":
				sounder.turn(comand);
				break;
			case "R":
				sounder.turn(comand);
				break;
			case "M":
				//colocar verificações do terreno aqui
				sounder.move();
				break;
				default:
					System.out.println("Invalid Comand" );
					
			}
		}
	}
}
