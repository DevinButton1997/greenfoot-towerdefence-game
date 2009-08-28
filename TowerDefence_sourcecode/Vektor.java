/**
 * TowerDefence
 * Copyright (C) 2008-2009 Kevin 'Blackdead' Huber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/
 
/**
 * Write a description of class Vektor here.
 * 
 * @author (Kevin Huber)
 * @version (1.1)
 */
public class Vektor
{
    // instance variables - replace the example below with your own
    public double x;
    public double y;

    public Vektor(double pX, double pY)
    {
        x = pX;
        y = pY;
    }
    
    public double getLength()
    {
        return Math.hypot(x, y);
    }
    
    public double skalarprodukt(Vektor v1, Vektor v2)
    {
        return ((v1.x*v2.x) + (v1.y*v2.y));
    }
    
    public double getAngle(Vektor v1, Vektor v2)
    {
        
        double bogenmaﬂ = Math.acos(skalarprodukt(v1, v2)/(v1.getLength()*v2.getLength()));
        
        return (360/(2*Math.PI))*bogenmaﬂ;
    }
}
