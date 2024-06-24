import {Component, Input, OnInit} from '@angular/core';
import colors from "tailwindcss/colors";

@Component({
  selector: 'app-option',
  templateUrl: './option.component.html',
  styleUrl: './option.component.scss'
})
export class OptionComponent implements OnInit {

  randomBorderColor: String | undefined;
  randomBackgroundColor: String | undefined;
  @Input() progessValue: number | undefined;
  @Input() ind: number | undefined;
  @Input() text: String | undefined;

  ngOnInit(): void {

    const randomPick = this.pickColorBasedOnIndex([
      ["bg-red-100", "border-red-300"],
      ["bg-pink-100", "border-pink-300"],
      ["bg-purple-100", "border-purple-300"],
      ["bg-indigo-100", "border-indigo-300"],
      ["bg-blue-100", "border-blue-300"],
      ["bg-cyan-100", "border-cyan-300"],
      ["bg-teal-100", "border-teal-300"],
      ["bg-green-100", "border-green-300"],
      ["bg-lime-100", "border-lime-300"],
      ["bg-yellow-100", "border-yellow-300"],
      ["bg-amber-100", "border-amber-300"],
      ["bg-orange-100", "border-orange-300"],
    ], this.ind);
    this.randomBackgroundColor = randomPick[0];
    this.randomBorderColor = randomPick[1];
  }

  calculateClasses() {
    return "relative overflow-hidden shadow p-4 mt-2 rounded-3xl font-semibold border " + ` ${this.randomBorderColor}`
  }

  pickColorBasedOnIndex(colors: String[][], index: number | undefined): String[] {
    const len = colors.length;
    let pickedIndex;

    if (index === undefined) {
      pickedIndex = Math.floor(Math.random() * len);
    } else {
      pickedIndex = index % len;
    }

    return colors[pickedIndex];
  }

  calculateProgressClasses(): String {
    let ret = "absolute left-0 top-0 bottom-0 h-full " + this.randomBackgroundColor;

    // Needed so that tailwind generates the classes for the percentages
    const percentages = "w-[0%] w-[1%] w-[2%] w-[3%] w-[4%] w-[5%] w-[6%] w-[7%] w-[8%] " +
      "w-[9%] w-[10%] w-[11%] w-[12%] w-[13%] w-[14%] w-[15%] w-[16%] w-[17%] w-[18%] w-[19%] w-[20%] " +
      "w-[21%] w-[22%] w-[23%] w-[24%] w-[25%] w-[26%] w-[27%] w-[28%] w-[29%] w-[30%] w-[31%] w-[32%] " +
      "w-[33%] w-[34%] w-[35%] w-[36%] w-[37%] w-[38%] w-[39%] w-[40%] w-[41%] w-[42%] w-[43%] w-[44%] " +
      "w-[45%] w-[46%] w-[47%] w-[48%] w-[49%] w-[50%] w-[51%] w-[52%] w-[53%] w-[54%] w-[55%] w-[56%] " +
      "w-[57%] w-[58%] w-[59%] w-[60%] w-[61%] w-[62%] w-[63%] w-[64%] w-[65%] w-[66%] w-[67%] w-[68%] " +
      "w-[69%] w-[70%] w-[71%] w-[72%] w-[73%] w-[74%] w-[75%] w-[76%] w-[77%] w-[78%] w-[79%] w-[80%] " +
      "w-[81%] w-[82%] w-[83%] w-[84%] w-[85%] w-[86%] w-[87%] w-[88%] w-[89%] w-[90%] w-[91%] w-[92%] " +
      "w-[93%] w-[94%] w-[95%] w-[96%] w-[97%] w-[98%] w-[99%] w-[100%]";

    if (this.progessValue == undefined) {
      ret += ` w-[5%]`
    } else {
      ret += ` w-[${this.progessValue}%]`
    }
    ret += ` ${this.randomBackgroundColor}`

    return ret;

  }

}
