package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class Gantt extends PApplet
{	
	//ArrayList
	ArrayList<Task> tasks = new ArrayList<Task>();

	//global variables
	float barHeight = 30;
	int flag;
	
	public void settings()
	{
		size(800, 600);
	}

	//load tasks
	public void loadTasks()
	{
		Table t = loadTable("tasks.csv", "header");
		for(TableRow tr:t.rows())
		{
			Task tk = new Task(tr);
			tasks.add(tk);
		}
	}

	//print tasks
	public void printTasks()
	{
		for(Task tk:tasks)
		{
			println(tk);
		}
	}
	
	public void mousePressed()
	{
		flag = checkForObject();
		
	}

	

	public int checkForObject(){

		float border = width * 0.05f;

		//iterate through ArrayList
		for (int i = 0; i < 9; i++) {

            Task t = tasks.get(i);

            //must be between two y coordinates
			float m = map(i, 0, 9, 100, 500);
			float y1 = m - 10;
            float y2 = y1 + barHeight;


			//must be between two x coordinates
			//start
			float x1 = map(t.getStart(), 1, 30, 150, width - border);
			//end
			float x2 = map(t.getEnd(), 1, 30, 150, width);
			
			//print(x1 + x2 + y1 + y2);

			//returns task index
			if(mouseX > x1 && mouseX < x2
				&& mouseY > y1 && mouseY < y2)
			{
				return i;
			}
            
		}
		return -1;
	
		
	}


	


	public void mouseDragged()
	{
		if(flag != -1){
			Task t = tasks.get(flag);
			
			if(mouseX < 400){
				if(t.getStart() > 1 && t.getEnd() < 30){
					t.setEnd(t.getEnd()+1);	
				}
			}
			if(mouseX > 400){
				if(t.getStart() > 1 && t.getEnd() < 30){
					t.setEnd(t.getEnd()-1);	
				}
			}
		}
		

	}

	
	
	public void setup() 
	{
		loadTasks();
		printTasks();
	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}


	public void displayTasks(){
		
		float border = width * 0.05f;

		fill(255);
		stroke(0, 0, 255);
		textAlign(CENTER, CENTER);

		for(int i = 1; i <= 30; i++){
			float x = map(i, 1, 30, 150, width - border);
			line(x, border, x, height - border);
			text(i, x, border/2);
		}

		for(int i = 0; i < tasks.size(); i++){
			
			//styling
			colorMode(RGB);
			fill(255);
			stroke(255);
			
			//new Task object to iterate through ArrayList
			Task myTask = new Task();
			myTask = tasks.get(i);

			//tasks on left 
			float x = map(i, 0, 9, 100, 500);
			text(myTask.getTask(), border*2, x);

			//map start and end values out
			float j = map(myTask.getStart(), 1, 30, 150, width - border);
			float k = map(myTask.getEnd(), 1, 30, 150, width - border);
			
			//styling for bars
			noStroke();
			colorMode(HSB);
			float l = map(i, 0, 9, 0, 250);
			fill(l, 300, 300);

			//x1, y1, end length - start = width, height 
			rect(j, x - 10, k-j, barHeight);
			
			
		}

	}
}
