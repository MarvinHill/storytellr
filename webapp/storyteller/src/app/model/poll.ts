export interface Poll {
    id : string
    owner: string
    question : string
    options : PollOption[]
}

export interface PollOption {
    id : string
    content : string
    voteCount : number
}




