import {Poll, PollOption} from "../../../../model/poll";
import {isEmpty} from "rxjs";

/**
 * PollTool is a class that represents a tool for creating and managing polls.
 * It provides methods for rendering the poll, creating new options, and saving the poll.
 */
export class PollTool {

  /**
   * Returns the toolbox configuration for the PollTool.
   * @returns {Object} The toolbox configuration.
   */
  static get toolbox() {
    return {
      title: 'Community Question',
      icon: `
<svg xmlns="http://www.w3.org/2000/svg" height="14px" viewBox="0 -960 960 960" width="14px" fill="#000000"><path d="M480-560h200v-80H480v80Zm0 240h200v-80H480v80ZM360-520q33 0 56.5-23.5T440-600q0-33-23.5-56.5T360-680q-33 0-56.5 23.5T280-600q0 33 23.5 56.5T360-520Zm0 240q33 0 56.5-23.5T440-360q0-33-23.5-56.5T360-440q-33 0-56.5 23.5T280-360q0 33 23.5 56.5T360-280ZM200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z"/></svg>`
    };
  }

  /**
   * Constructor for the PollTool.
   * @param {Object} obj - The object containing the api and data for the PollTool.
   */
  constructor(obj : {api : any, data : Poll}) {
    this.poll = obj.data;
    this.api = obj.api;
  }

  poll : Poll | undefined;
  api : any
  element : HTMLElement | undefined;

  /**
   * Renders the PollTool.
   * @returns {HTMLElement | undefined} The HTML element for the PollTool.
   */
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

    this.addExistingOption(this.poll.options);


    const questionInput : HTMLInputElement | null = this.element.querySelector("#questionInput");
    if(questionInput){
      questionInput.value = this.poll.question;
      questionInput.addEventListener("input", (event) => {
        if(this.poll){
          this.poll.question = (event.target as HTMLInputElement).value;
        }
      });
    }

    this.element.querySelector("#submitButton")?.addEventListener("click", () => {
      // get the value of the input field
      const optionInput : HTMLInputElement | null | undefined = this.element?.querySelector("#optionInput");
      if(optionInput){
        if(optionInput.value === ""){
          this.addNewOption("New Option");
        }
        else {
          this.addNewOption(optionInput.value);
        }
        optionInput.value = "";
      }
    });

    return this.element;
  }

  /**
   * Creates an HTML element for an option.
   * @param {PollOption} option - The option to create an HTML element for.
   * @returns {HTMLElement} The HTML element for the option.
   */
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
        }
      });
    }
    return newEl;
  }

  /**
   * Adds a new option to the PollTool.
   * @param {String} content - The content for the new option.
   */
  addNewOption(content : String) {
    let pollOption : PollOption = {content: content, voteCount: 0, id: `REPLACE${Math.floor(Math.random() * 100000)}`} as PollOption;
    this.element?.querySelector("#content")?.appendChild(this.createOptionElement(pollOption));
    this.poll?.options.push(pollOption)
  }

  /**
   * Adds existing options to the PollTool.
   * @param {PollOption[]} options - The existing options to add to the PollTool.
   */
  addExistingOption(options : PollOption[]) {
    if(options.length == 0){
      return;
    }
    for(let pollOption of options){
      this.element?.querySelector("#content")?.appendChild(this.createOptionElement(pollOption));

    }
  }

  /**
   * Saves the poll.
   * @param {any} blockContent - The content of the block to save.
   * @returns {Poll | undefined} The saved poll.
   */
  save(blockContent: any) {
    return this.poll
  }

}
