package com.example.myapplication;

public class Calculator
{
    public static double CalculateGPA(int[] Weights, int[] Scores)
    {
        int CreditHoursCount = 0;
        int Total = 0;

        for (int i = 0; i < 6; i++)
        {
            CreditHoursCount += Weights[i];
        }

        for (int i = 0; i < 6; i++)
        {
            Total += Weights[i] * Scores[i];
        }

        System.out.println(Total);
        System.out.println(CreditHoursCount);
        return (double) Total / CreditHoursCount;
    }
}
