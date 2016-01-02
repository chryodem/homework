#ifndef PONG_H_
#define PONG_H_

typedef struct
{
	int x;
	int y;
	int oldx;
	int oldpage1;
	int oldpage2;
} BALL;
int drawBall(BALL* ball);

typedef struct
{
	int x;
	int y;
	int oldpage;
} PADDLE;
int drawPaddle(PADDLE* paddle);

#endif /*PONG_H_*/
