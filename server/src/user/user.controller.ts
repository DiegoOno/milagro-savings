import { Controller, Get, Post, Body, Patch, Param } from '@nestjs/common';
import { UserService } from './user.service';
import { User } from './entities/user.entity';

@Controller('user')
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Post()
  create(@Body() user: User) {
    return this.userService.create(user);
  }

  @Get()
  findAll() {
    return this.userService.findAll();
  }

  @Get(':id')
  findById(@Param('id') id: number) {
    return this.userService.findById(id);
  }

  @Patch(':id/activate')
  activate(@Param('id') id: number) {
    return this.userService.activate(id);
  }

  @Patch(':id/disable')
  disable(@Param('id') id: number) {
    return this.userService.disable(id);
  }

  @Patch(':id')
  update(@Param('id') id: number, @Body() user: User) {
    return this.userService.update(id, user);
  }
}
