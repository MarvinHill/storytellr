import { UpdatePollOption, PollOption } from "../../../../model/poll";

export class PollTool {
    static get toolbox() {
      return {
        title: 'Community Question',
        icon: `
<svg xmlns="http://www.w3.org/2000/svg" height="14px" viewBox="0 -960 960 960" width="14px" fill="#000000"><path d="M480-560h200v-80H480v80Zm0 240h200v-80H480v80ZM360-520q33 0 56.5-23.5T440-600q0-33-23.5-56.5T360-680q-33 0-56.5 23.5T280-600q0 33 23.5 56.5T360-520Zm0 240q33 0 56.5-23.5T440-360q0-33-23.5-56.5T360-440q-33 0-56.5 23.5T280-360q0 33 23.5 56.5T360-280ZM200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z"/></svg>`
      };
    }

    constructor({data}: {data: {pollId: string}}){
      if(data){
        this.pollId = data.pollId;
      }
    }

    pollId : string = "";
  
    render(){

      let el = document.createElement('div');

      if(this.pollId == ""){
        const err = createPoll().then((res) => {
          if(res.ok){
            console.log("Poll created");
            res.json().then((data : PollOption) => {
              this.pollId = data.id;
              this.render();
            })
          }
        });
      }
      else {
        // get poll
      }


      return el;
    }
  
    save(blockContent : any){
      return {
        pollId: this.pollId,
      }
    }

  }

  function  createPoll(){
    return fetch("/api/v1/polls/create", {method: "POST"});
  }

  function getPoll(pollId : string){
    return fetch(`/api/v1/polls/${pollId}`, {method: "GET"});
  }

  function createPollOption(pollId : string){
    return fetch(`/api/v1/polls/${pollId}/pollOptions/create`, {method: "POST"});
  }

  function deletePollOption(pollId : string, optionId : string){
    return fetch(`/api/v1/polls/${pollId}/pollOptions/${optionId}`, {method: "DELETE"});
  }

  function updatePollOption(pollId : string, optionId : string, editContent : UpdatePollOption){
    return fetch(`/api/v1/polls/${pollId}/pollOptions/${optionId}/update`, {method: "POST", body: JSON.stringify(editContent)});
  }