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
		checkForObject();
		
			
	}

	public int checkForObject(){

		//iterate through ArrayList
		for (int i = 0; i < 9; i++) {

            Task t = tasks.get(i);

            //must be between two y coordinates
            float y1 = map(i, 0, 9, 100, 500);
            float y2 = y1;


            //must be between two x coordinates
            float x1 = map(t.getStart(), 1, 30, 145, 800);
			float x2 = map(t.getEnd(), 1, 30, 145, 800);
            
            // calculating the y axis ie this is used to
            // allign the rectangles with the writing
           
            
            if(mouseX >= x1 && mouseX <= x2 
            && mouseY >= y1   && mouseY <= y2){
                println("clicked");
                return i;
            }

    

		}
		
		return 0;
        
	}


	


	public void mouseDragged()
	{
		
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
