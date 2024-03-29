//******************************************************************************
//  dicePics.c
//
//******************************************************************************
//	Author:			Paul Roper
//	Revision:		1.0		02/15/2010
//					1.1		11/04/2010	outDie added
//
//	Description:	Controller firmware for NHD-C160100DiZ-FSW-FBW LCD
//		Output die frame and die values 0-6.
//
//******************************************************************************

#include "msp430x22x4.h"
#include "eZ430X.h"
#include "lcd.h"

const unsigned char dice1[] = { 8, 8,
//   12345678.
// 0 XXXXXXXX.
// 1 XXXXXXXX.
// 2 XX      .
// 3 XX      .
// 4 XX      .
// 5 XX      .
// 6 XX      .
// 7 XX      .
	0xff, 0xff, 0xc0, 0xc0, 0xc0, 0xc0, 0xc0, 0xc0 };

const unsigned char dice2[] = { 8, 8,
//   12345678.
// 0 XXXXXXXX.
// 1 XXXXXXXX.
// 2         .
// 3         .
// 4         .
// 5         .
// 6         .
// 7         .
	0xc0, 0xc0, 0xc0, 0xc0, 0xc0, 0xc0, 0xc0, 0xc0 };

const unsigned char dice3[] = { 8, 8,
//   12345678.
// 0 XXXXXXXX.
// 1 XXXXXXXX.
// 2       XX.
// 3       XX.
// 4       XX.
// 5       XX.
// 6       XX.
// 7       XX.
	0xc0, 0xc0, 0xc0, 0xc0, 0xc0, 0xc0, 0xff, 0xff };

const unsigned char dice4[] = { 8, 8,
//   12345678.
// 0 XX      .
// 1 XX      .
// 2 XX      .
// 3 XX      .
// 4 XX      .
// 5 XX      .
// 6 XX      .
// 7 XX      .
	0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

const unsigned char dice5[] = { 8, 8,
//   12345678.
// 0         .
// 1         .
// 2         .
// 3         .
// 4         .
// 5         .
// 6         .
// 7         .
	0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

const unsigned char dice6[] = { 8, 8,
//   12345678.
// 0       XX.
// 1       XX.
// 2       XX.
// 3       XX.
// 4       XX.
// 5       XX.
// 6       XX.
// 7       XX.
	0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff };

const unsigned char dice7[] = { 8, 8,
//   12345678.
// 0 XX      .
// 1 XX      .
// 2 XX      .
// 3 XX      .
// 4 XX      .
// 5 XX      .
// 6 XXXXXXXX.
// 7 XXXXXXXX.
	0xff, 0xff, 0x03, 0x03, 0x03, 0x03, 0x03, 0x03 };

const unsigned char dice8[] = { 8, 8,
//   12345678.
// 0         .
// 1         .
// 2         .
// 3         .
// 4         .
// 5         .
// 6 XXXXXXXX.
// 7 XXXXXXXX.
	0x03, 0x03, 0x03, 0x03, 0x03, 0x03, 0x03, 0x03 };

const unsigned char dice9[] = { 8, 8,
//   12345678.
// 0       XX.
// 1       XX.
// 2       XX.
// 3       XX.
// 4       XX.
// 5       XX.
// 6 XXXXXXXX.
// 7 XXXXXXXX.
	0x03, 0x03, 0x03, 0x03, 0x03, 0x03, 0xff, 0xff };

const unsigned char spot[] = { 16, 16,
//   12345678.90123456.
// 0      XXX.XXX     .
// 1    XXXXX.XXXXX   .
// 2   XXXXXX.XXXXXX  .
// 3  XXXXXXX.XXXXXXX .
// 4  XXXXXXX.XXXXXXX .
// 5 XXXXXXXX.XXXXXXXX.
// 6 XXXXXXXX.XXXXXXXX.
// 7 XXXXXXXX.XXXXXXXX.
	0x07, 0x1f, 0x3f, 0x7f, 0x7f, 0xff, 0xff, 0xff,
	0xff, 0xff, 0xff, 0x7f, 0x7f, 0x3f, 0x1f, 0x07,
// 0 XXXXXXXX.XXXXXXXX.
// 1 XXXXXXXX.XXXXXXXX.
// 2 XXXXXXXX.XXXXXXXX.
// 3  XXXXXXX.XXXXXXX .
// 4  XXXXXXX.XXXXXXX .
// 5   XXXXXX.XXXXXX  .
// 6    XXXXX.XXXXX   .
// 7      XXX.XXX     .
	0xe0, 0xf8, 0xfc, 0xfe, 0xfe, 0xff, 0xff, 0xff,
	0xff, 0xff, 0xff, 0xfe, 0xfe, 0xfc, 0xf8, 0xe0 };

const unsigned char smallSpot[] = { 16, 16,
//   12345678.90123456.
// 0         .        .
// 1     XXXX.XXXX    .
// 2    XXXXX.XXXXX   .
// 3   XXXXXX.XXXXXX  .
// 4   XXXXXX.XXXXXX  .
// 5  XXXXXXX.XXXXXXX .
// 6  XXXXXXX.XXXXXXX .
// 7  XXXXXXX.XXXXXXX .
	0x00, 0x07, 0x1f, 0x3f, 0x7f, 0x7f, 0x7f, 0x7f,
	0x7f, 0x7f, 0x7f, 0x7f, 0x3f, 0x1f, 0x07, 0x00,
// 0  XXXXXXX.XXXXXXX .
// 1  XXXXXXX.XXXXXXX .
// 2  XXXXXXX.XXXXXXX .
// 3   XXXXXX.XXXXXX  .
// 4   XXXXXX.XXXXXX  .
// 5    XXXXX.XXXXX   .
// 6     XXXX.XXXX    .
// 7         .        .
	0x00, 0xf0, 0xf8, 0xfc, 0xfe, 0xfe, 0xfe, 0xfe,
	0xfe, 0xfe, 0xfe, 0xfe, 0xfc, 0xf8, 0xf0, 0x00 };

const unsigned char blankSpot[] = { 16, 16,
//   12345678.90123456.
// 0         .        .
// 1         .        .
// 2         .        .
// 3         .        .
// 4         .        .
// 5         .        .
// 6         .        .
// 7         .        .
	0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
// 0         .        .
// 1         .        .
// 2         .        .
// 3         .        .
// 4         .        .
// 5         .        .
// 6         .        .
// 7         .        .
	0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
	0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

void drawDieFrame(int x)
{
	lcd_image(dice1, x+0, 11);
	lcd_image(dice2, x+8, 11);
	lcd_image(dice2, x+16, 11);
	lcd_image(dice2, x+24, 11);
	lcd_image(dice2, x+32, 11);
	lcd_image(dice2, x+40, 11);
	lcd_image(dice2, x+48, 11);
	lcd_image(dice3, x+56, 11);

	lcd_image(dice4, x+0, 10);
	lcd_image(dice6, x+56, 10);

	lcd_image(dice4, x+0, 9);
	lcd_image(dice6, x+56, 9);

	lcd_image(dice4, x+0, 8);
	lcd_image(dice6, x+56, 8);

	lcd_image(dice4, x+0, 7);
	lcd_image(dice6, x+56, 7);

	lcd_image(dice4, x+0, 6);
	lcd_image(dice6, x+56, 6);

	lcd_image(dice4, x+0, 5);
	lcd_image(dice6, x+56, 5);

	lcd_image(dice7, x+0, 4);
	lcd_image(dice8, x+8, 4);
	lcd_image(dice8, x+16, 4);
	lcd_image(dice8, x+24, 4);
	lcd_image(dice8, x+32, 4);
	lcd_image(dice8, x+40, 4);
	lcd_image(dice8, x+48, 4);
	lcd_image(dice9, x+56, 4);
	return;
}


void drawDie(int die, int x)
{
	switch (die)
	{
		case 0:
		{
			lcd_image(blankSpot, x, 10);
			lcd_image(blankSpot, x, 8);
			lcd_image(blankSpot, x, 6);

			lcd_image(blankSpot, x+16, 8);

			lcd_image(blankSpot, x+32, 10);
			lcd_image(blankSpot, x+32, 8);
			lcd_image(blankSpot, x+32, 6);
			break;
		}

		case 1:
		{
			lcd_image(blankSpot, x, 10);
			lcd_image(blankSpot, x, 8);
			lcd_image(blankSpot, x, 6);

			lcd_image(spot, x+16, 8);

			lcd_image(blankSpot, x+32, 10);
			lcd_image(blankSpot, x+32, 8);
			lcd_image(blankSpot, x+32, 6);
			break;
		}

		case 2:
		{
			lcd_image(spot, x, 10);
			lcd_image(blankSpot, x, 8);
			lcd_image(blankSpot, x, 6);

			lcd_image(blankSpot, x+16, 8);

			lcd_image(blankSpot, x+32, 10);
			lcd_image(blankSpot, x+32, 8);
			lcd_image(spot, x+32, 6);
			break;
		}

		case 3:
		{
			lcd_image(spot, x, 10);
			lcd_image(blankSpot, x, 8);
			lcd_image(blankSpot, x, 6);

			lcd_image(spot, x+16, 8);

			lcd_image(blankSpot, x+32, 10);
			lcd_image(blankSpot, x+32, 8);
			lcd_image(spot, x+32, 6);
			break;
		}

		case 4:
		{
			lcd_image(spot, x, 10);
			lcd_image(blankSpot, x, 8);
			lcd_image(spot, x, 6);

			lcd_image(blankSpot, x+16, 8);

			lcd_image(spot, x+32, 10);
			lcd_image(blankSpot, x+32, 8);
			lcd_image(spot, x+32, 6);
			break;
		}

		case 5:
		{
			lcd_image(spot, x, 10);
			lcd_image(blankSpot, x, 8);
			lcd_image(spot, x, 6);

			lcd_image(spot, x+16, 8);

			lcd_image(spot, x+32, 10);
			lcd_image(blankSpot, x+32, 8);
			lcd_image(spot, x+32, 6);
			break;
		}

		case 6:
		{
			lcd_image(smallSpot, x, 10);
			lcd_image(smallSpot, x, 8);
			lcd_image(smallSpot, x, 6);

			lcd_image(blankSpot, x+16, 8);

			lcd_image(smallSpot, x+32, 10);
			lcd_image(smallSpot, x+32, 8);
			lcd_image(smallSpot, x+32, 6);
			break;
		}
	}
	return;
} // end outDie()
