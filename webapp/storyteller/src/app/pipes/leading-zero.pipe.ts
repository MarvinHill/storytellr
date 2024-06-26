import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'leadingZero'
})

/**
 * LeadingZeroPipe is a custom pipe that adds a leading zero to a number if it is less than 10.
 */
export class LeadingZeroPipe implements PipeTransform {

  /**
   * Adds a leading zero to a number if it is less than 10.
   * @param value The number to add a leading zero to.
   */
  transform(value: any): any {
    if (value < 10) {
      return '0' + value;
    }
    return value;
  }
}
