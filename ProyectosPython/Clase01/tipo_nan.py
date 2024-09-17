import math
from decimal import Decimal

#NaN (Not a number)
a = float('NaN')
print(f'a:{a}')

#Modulo math
a = float('nan')
print(f'Es de tipo NaN(Not a Number)?: {math.isnan(a)}')

#Modulo decimal
a = Decimal('2.0')
print(f'Es de tipo NaN(Not a Number)?: {math.isnan(a)}')