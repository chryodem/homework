
#define lineTo
void lineTo(int x0, int y0, int x1, int y1);
#define XSTEP(a,b) =(a-b)/160
#define YSTEP(a,b) = (a-b)/160



void lineTo(int x0, int y0, int x1, int y1)	//function to draw the line

{
//	long int dx = x1 - x0;
//	long int dy = y1 - y0;
//	float xMover = (float)x0; //floating number 
//	float yMover = (float)y0; //old y
	float oldX = (float)x0;
	float oldY = (float)y0;
	float newX = (float)x1;
	float newY = (float)y1;
	
	float xStep = XSTEP(newX,oldX)//(newX-oldX)/160;
	float yStep =YSTEP(newY,oldY)//160;
	int i;
	for( i =0; i<160;i++)
	{
		lcd_point(oldX,oldY,line_drawer);
		oldX+=xStep;
		oldY+=yStep;
	}

}