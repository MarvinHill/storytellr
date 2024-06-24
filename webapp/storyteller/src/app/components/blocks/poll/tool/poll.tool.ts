import {Poll, PollOption} from "../../../../model/poll";
import {isEmpty} from "rxjs";

export class PollTool {
  static get toolbox() {
    return {
      title: 'Community Question',
      icon: `
<svg xmlns="http://www.w3.org/2000/svg" height="14px" viewBox="0 -960 960 960" width="14px" fill="#000000"><path d="M480-560h200v-80H480v80Zm0 240h200v-80H480v80ZM360-520q33 0 56.5-23.5T440-600q0-33-23.5-56.5T360-680q-33 0-56.5 23.5T280-600q0 33 23.5 56.5T360-520Zm0 240q33 0 56.5-23.5T440-360q0-33-23.5-56.5T360-440q-33 0-56.5 23.5T280-360q0 33 23.5 56.5T360-280ZM200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z"/></svg>`
    };
  }

  // @ts-ignore
  constructor({api, data}) {
    console.log("API", api);
    console.log("Data", data);
    this.poll = data;
    this.api = api;
  }

  poll : Poll | undefined;
  api : any
  element : HTMLElement | undefined;

  render() {
    if(this.poll === undefined || this.poll.id === undefined ) {
      this.poll = {owner:"", question:"NEW QUESTION1", options:[], id:""} as Poll;
    }

    if(this.poll.options == undefined){
      this.poll.options = [];
    }

    this.element = document.createElement("div")

    this.element.innerHTML = `
    <div class="p-4 flex w-full justify-center">
    <div class="flex flex-col w-full sm:w-96 bg-white p-4 rounded-3xl shadow font-quicksand">
      <input id="questionInput" type="text" class="w-full p-2 mt-2 text-center font-extrabold text-2xl" placeholder="Option Text">
      <section id="content">
      </section>
    </div>
    </div>

    <div class="p-4">
     <button id="submitButton" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Add Option</button>
     <input id="optionInput" type="text" class="w-96 p-2 mt-2" placeholder="Option Text">
    </div>
    `
    console.log("Poll", this.poll);

    // for (const pollOption of this.poll.options) {
    //   this.addExistingOption(pollOption);
    //   stop
    // }

    this.addExistingOption(this.poll.options);

    const questionInput : HTMLInputElement | null = this.element.querySelector("#questionInput");
    if(questionInput){
      questionInput.value = this.poll.question;
      questionInput.addEventListener("input", (event) => {
        if(this.poll){
          this.poll.question = (event.target as HTMLInputElement).value;
          console.log("Poll", this.poll);
        }
      });
    }

    this.poll.options.map(option => {
      return this.createOptionElement(option);
    }).forEach(newEl => {
        this.element?.querySelector("#content")?.appendChild(newEl);
    }
    );

    this.element.querySelector("#submitButton")?.addEventListener("click", () => {
      this.addNewOption();
    });

    return this.element;
  }

  createOptionElement(option: PollOption) {
    const templateOption = `
        <div class="flex w-full">
        <div class="relative overflow-hidden shadow p-4 mt-2 rounded-3xl font-semibold border border-red-300 w-full">
            <p id="optionText" class="relative z-20">SomeOptionText</p>
        </div>
        <button id="closeButton"> <img src="assets/icons/close.svg"> </button>
        </div>
    `

    const newEl = document.createElement("section")
    newEl.innerHTML = templateOption;
    const textElement = newEl.querySelector("#optionText");
    if( textElement !== null) {
      textElement.textContent = option.content;
    }
    const closeButton = newEl.querySelector("#closeButton");
    if(closeButton){
      closeButton.addEventListener("click", () => {
        newEl.remove();
        if(this.poll){
          this.poll.options = this.poll.options.filter(opt => opt.id !== option.id);
          const index = this.api.blocks.getCurrentBlockIndex()
          this.api.blocks.insert("paragraph", {text: " "}, {}, index + 1, false);
          this.api.blocks.delete(index + 1);
          console.log(this.poll.options);
        }
      });
    }
    return newEl;
  }

  addNewOption() {
    console.log("Editor: Add new Option")
    let pollOption : PollOption = {content: "New Option", voteCount: 0, id: `REPLACE${Math.floor(Math.random() * 100000)}`} as PollOption;
    this.element?.querySelector("#content")?.appendChild(this.createOptionElement(pollOption));
    this.poll?.options.push(pollOption)
    console.log("Poll", this.poll)
  }

  addExistingOption(options : PollOption[]) {
    // console.log("Editor: Add existing Option")
    // let pollOption : PollOption = {content: option.content, voteCount: option.voteCount, id: option.id} as PollOption;
    // this.element?.querySelector("#content")?.appendChild(this.createOptionElement(pollOption));
    // console.log("Poll", this.poll)
    if(options.length == 0){
      return;
    }
    for(let pollOption of options){
      this.element?.querySelector("#content")?.appendChild(this.createOptionElement(pollOption));

    }
    console.log("Existing options was called")
  }

  save(blockContent: any) {
    return this.poll
  }

}
