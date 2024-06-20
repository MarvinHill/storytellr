export interface Poll {
    id : string
    owner: string
    bookId : string
    question : string
    options : PollOption[]
}

export interface PollOption {
    id : string
    pollId : string
    content : string
    voteCount : number
}

export interface CreatePollRequest {
    bookId : string
}

export interface UpdatePollOption {
    pollId : string
    content : string
}

export interface DeletePollOption {
    optionId : string,
    pollId : string
}

export interface CreatePollOption {
    pollId : string
}


