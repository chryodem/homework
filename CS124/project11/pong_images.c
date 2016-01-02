//******************************************************************************
//  pong_images.c
//
//  Description:
//
//  Author:		Paul Roper, Brigham Young University
//  Revisions:	March 2010	Original code
//				07/28/2011	Updated
//
//  Built with Code Composer Studio Version: 4.2.3.00004 
//*******************************************************************************

#include "msp430x22x4.h"
#include <stdlib.h>
#include "eZ430X.h"
#include "lcd.h"
#include "pong.h"

//*******************************************************************************
//	ball images
//
//	0/8  0/7  0/6  0/5  12/4  11/3  10/2  9/1
//	4/0  5/0  6/0  7/0  8/0  9/1 10/2 11/3

//    0     1     2     3     4     5     6     7     8     9    10    11    12
//	00000 0XXX0 XXXXX XXXXX XXXXX 0XXX0 00000 00000 00000 00000 00000 00000 00000
//	00000 00000 0XXX0 XXXXX XXXXX XXXXX 0XXX0 00000 00000 00000 00000 00000 00000
//	00000 00000 00000 0XXX0 XXXXX XXXXX XXXXX 0XXX0 00000 00000 00000 00000 00000
//	00000 00000 00000 00000 0XXX0 XXXXX XXXXX XXXXX 0XXX0 00000 00000 00000 00000
//	00000 00000 00000 00000 00000 0XXX0 XXXXX XXXXX XXXXX 0XXX0 00000 00000 00000
//	00000 00000 00000 00000 00000 00000 0XXX0 XXXXX XXXXX XXXXX 0XXX0 00000 00000
//	00000 00000 00000 00000 00000 00000 00000 0XXX0 XXXXX XXXXX XXXXX 0XXX0 00000
//	00000 00000 00000 00000 00000 00000 00000 00000 0XXX0 XXXXX XXXXX XXXXX 0XXX0

const unsigned char b_00[] = { 5, 8, 0x00, 0x00, 0x00, 0x00, 0x00 };
const unsigned char b_01[] = { 5, 8, 0x00, 0x80, 0x80, 0x80, 0x00 };
const unsigned char b_02[] = { 5, 8, 0x80, 0xc0, 0xc0, 0xc0, 0x80 };
const unsigned char b_03[] = { 5, 8, 0xc0, 0xe0, 0xe0, 0xe0, 0xc0 };
const unsigned char b_04[] = { 5, 8, 0xe0, 0xf0, 0xf0, 0xf0, 0xe0 };
const unsigned char b_05[] = { 5, 8, 0x70, 0xf8, 0xf8, 0xf8, 0x70 };
const unsigned char b_06[] = { 5, 8, 0x38, 0x7c, 0x7c, 0x7c, 0x38 };
const unsigned char b_07[] = { 5, 8, 0x1c, 0x3e, 0x3e, 0x3e, 0x1c };
const unsigned char b_08[] = { 5, 8, 0x0e, 0x1f, 0x1f, 0x1f, 0x0e };
const unsigned char b_09[] = { 5, 8, 0x07, 0x0f, 0x0f, 0x0f, 0x07 };
const unsigned char b_10[] = { 5, 8, 0x03, 0x07, 0x07, 0x07, 0x03 };
const unsigned char b_11[] = { 5, 8, 0x01, 0x03, 0x03, 0x03, 0x01 };
const unsigned char b_12[] = { 5, 8, 0x00, 0x01, 0x01, 0x01, 0x00 };

//	                                                            12 11 10  9 ...
//	                                                            -- -- -- --
//	                                    12 11 10  9  8  7  6  5  4  3  2  1
//	                                    -- -- -- -- 
//	            12 11 10  9  8  7  6  5  4  3  2  1
//	            -- -- -- --
//	 8  7  6  5  4  3  2  1

const unsigned char* balls[][2] = {
	{ b_00, b_08 },
	{ b_00, b_07 },
	{ b_00, b_06 },
	{ b_00, b_05 },
	{ b_12, b_04 },
	{ b_11, b_03 },
	{ b_10, b_02 },
	{ b_09, b_01 }
	};

//*******************************************************************************
//	paddle images
//
//	4 x 24
const unsigned char paddle1[] = { 4, 24, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
										 0xff, 0xff, 0x00, 0x00, 0x00, 0x00 };
const unsigned char paddle2[] = { 4, 24, 0x7f, 0x7f, 0x7f, 0x7f, 0xff, 0xff,
										 0xff, 0xff, 0x80, 0x80, 0x80, 0x80 };
const unsigned char paddle3[] = { 4, 24, 0x3f, 0x3f, 0x3f, 0x3f, 0xff, 0xff,
										 0xff, 0xff, 0xc0, 0xc0, 0xc0, 0xc0 };
const unsigned char paddle4[] = { 4, 24, 0x1f, 0x1f, 0x1f, 0x1f, 0xff, 0xff,
										 0xff, 0xff, 0xe0, 0xe0, 0xe0, 0xe0 };
const unsigned char paddle5[] = { 4, 24, 0x0f, 0x0f, 0x0f, 0x0f, 0xff, 0xff,
										 0xff, 0xff, 0xf0, 0xf0, 0xf0, 0xf0 };
const unsigned char paddle6[] = { 4, 24, 0x07, 0x07, 0x07, 0x07, 0xff, 0xff,
										 0xff, 0xff, 0xf8, 0xf8, 0xf8, 0xf8 };
const unsigned char paddle7[] = { 4, 24, 0x03, 0x03, 0x03, 0x03, 0xff, 0xff,
										 0xff, 0xff, 0xfc, 0xfc, 0xfc, 0xfc };
const unsigned char paddle8[] = { 4, 24, 0x01, 0x01, 0x01, 0x01, 0xff, 0xff,
										 0xff, 0xff, 0xfe, 0xfe, 0xfe, 0xfe };
const unsigned char paddle9[] = { 4, 24, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff,
										 0xff, 0xff, 0xff, 0xff, 0xff, 0xff };

const unsigned char* paddles[] = {	paddle1, paddle2, paddle3, paddle4, paddle5,
									paddle6, paddle7, paddle8, paddle9 };

//*******************************************************************************
//	draw paddle
//
int drawPaddle(PADDLE* myPaddle)
{
	unsigned char* paddleImage;
	int i, page;

	if (myPaddle->y > 84) myPaddle->y = 84;

	// paddle ranges from 24 to 99
	paddleImage = (unsigned char*)paddles[8 - (myPaddle->y & 0x0007)];
	page = (myPaddle->y >> 3) + 2;
	if (page > 12) page = 12;
	// output paddle
	lcd_image(paddleImage, myPaddle->x, page);

	// erase old paddle stuff
	for (i = 0; i < 3; i++, myPaddle->oldpage--)
	{
		if (myPaddle->oldpage >= 0)
		{
			if ( (myPaddle->oldpage != page) &&
			     (myPaddle->oldpage != (page-1)) &&
			     (myPaddle->oldpage != (page-2)) )
			{
				lcd_blank(myPaddle->x, myPaddle->oldpage, 4, 8);
			}
		}
	}
	// update paddle position
	myPaddle->oldpage = page;
	// return middle of paddle position
	return myPaddle->y;
} // end drawPaddle


//*******************************************************************************
//	draw ball
//
int drawBall(BALL* ball)
{
	char page1, page2;
	int index, oldx;

	if (ball->y > 99) ball->y = 99;
	page1 = (ball->y >> 3);
	index = ball->y & 0x0007;

	// draw ball
	if (index < 4)
	{	// 1 image ball
		page2 = 0;
		lcd_image(balls[index][1], ball->x, page1);	
	}
	else
	{	// 2 image ball
		page2 = page1 + 1;
		lcd_image(balls[index][0], ball->x, page2);
		lcd_image(balls[index][1], ball->x, page1);
	}

	// determine what needs to be erased
	oldx = ball->oldx;
	if (ball->oldpage1 == page1)
	{
		while (ball->x < oldx)
		{	// ball has moved to the left
			lcd_blank(oldx+4, page1, 1, 8);
			oldx--;
		}
		while (ball->x > oldx)
		{	// ball has moved to the right
			lcd_blank(oldx, page1, 1, 8);
			oldx++;
		}
	}
	else if ((ball->oldpage1 == page2) && ball->oldpage1)
	{
		while (ball->x < oldx)
		{	// ball has moved to the left
			lcd_blank(oldx+4, page2, 1, 8);
			oldx--;
		}
		while (ball->x > oldx)
		{	// ball has moved to the right
			lcd_blank(oldx, page2, 1, 8);
			oldx++;
		}
	}
	else
	{	// page change erase old ball
		lcd_blank(oldx, ball->oldpage1, 5, 8);
	}

	// look at ball 2
	if (ball->oldpage2)
	{
		oldx = ball->oldx;
		if (ball->oldpage2 == page2)
		{
			while (ball->x < oldx)
			{	// ball has moved to the left
				lcd_blank(oldx+4, page2, 1, 8);
				oldx--;
			}
			while (ball->x > oldx)
			{	// ball has moved to the right
				lcd_blank(oldx, page2, 1, 8);
				oldx++;
			}
		}
		else if (ball->oldpage2 == page1)
		{
			while (ball->x < oldx)
			{	// ball has moved to the left
				lcd_blank(oldx+4, page1, 1, 8);
				oldx--;
			}
			while (ball->x > oldx)
			{	// ball has moved to the right
				lcd_blank(oldx, page1, 1, 8);
				oldx++;
			}
		}
		else
		{	// page change erase old ball
			lcd_blank(oldx, ball->oldpage2, 5, 8);
		}
	}
	// update ball position
	ball->oldpage1 = page1;
	ball->oldpage2 = page2;
	ball->oldx = ball->x;

	// return ball vertical position
	return ball->y + 3;	
} // end drawBall
